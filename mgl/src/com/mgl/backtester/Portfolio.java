/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.backtester;

import com.mgl.io.Contract;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael G. Langer
 */
public class Portfolio {
    
    private static final int INIT_CASH = 100000;
    private BigDecimal cash = new BigDecimal(INIT_CASH);
    private static String SL = "0.97";
    
    private Map<Contract,ValueAmount> portfolio = new HashMap<Contract,ValueAmount>();
    
    
    public void buy(Contract contract, BigDecimal price, int amount) {
        if (!portfolio.containsKey(contract)) {
            BigDecimal value = price.multiply(new BigDecimal(amount));
            if (cash.compareTo(value) > 0) {
                cash = cash.subtract(value);
                BigDecimal stopValue = new BigDecimal(0); //price.multiply(new BigDecimal(SL));
                ValueAmount va = new ValueAmount(value, amount, price, stopValue);
                portfolio.put(contract, va);
                System.out.println("Buy: " + contract.getSymbol() + " - " + va.toString() + " PValue: " + getCash().toString() + " Cash: " + cash.toString());
            }
        } else {
            
        }
    }
    
    public void sell(Contract contract, BigDecimal price) {
         if (portfolio.containsKey(contract)) {
             ValueAmount va = portfolio.get(contract);
             BigDecimal m = price.multiply(new BigDecimal(va.amount));
             cash = cash.add(m);
             System.out.println("Profit: " + contract.getSymbol() + " Price: " + price.toString() +  " : " + m.subtract(va.value)+ " PValue: " + getCash().toString()+ " Cash: " + cash.toString());
             portfolio.remove(contract);
         }
    }
    
    public boolean contains(Contract contract) {
        return portfolio.containsKey(contract);
    }
    
    public BigDecimal getStop(Contract contract) {
        ValueAmount valueAmount = portfolio.get(contract);
        if (valueAmount != null) {
            return valueAmount.getStopValue();
        }
        return null;
    }
    
    public BigDecimal getPrice(Contract contract) {
        ValueAmount valueAmount = portfolio.get(contract);
        if (valueAmount != null) {
            return valueAmount.getPrice();
        }
        return null;
    }
    
    public void setStop(Contract contract, BigDecimal newStop) {
        ValueAmount valueAmount = portfolio.get(contract);
        if (valueAmount != null) {
            valueAmount.setStopValue(newStop);
        }
    }
    
    public void setLastPrice(Contract contract, BigDecimal lastPrice) {
         ValueAmount valueAmount = portfolio.get(contract);
        if (valueAmount != null) {
            valueAmount.setLastPrice(lastPrice);
        }
    }

    public BigDecimal getCash() {
        BigDecimal result = new BigDecimal(0);
        for (ValueAmount va : portfolio.values()) {
            result = result.add(va.getLastPrice().multiply(new BigDecimal(va.amount)));
        }
        return cash.add(result);
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
        
    private static class ValueAmount {
        BigDecimal value;
        int amount;        
        BigDecimal price;
        BigDecimal stopValue;
        BigDecimal lastPrice;

        public ValueAmount(BigDecimal value, int amount, BigDecimal price, BigDecimal stopValue) {
            this.value = value;
            this.amount = amount;
            this.price = price;
            this.stopValue = stopValue;
            this.lastPrice = price;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getStopValue() {
            return stopValue;
        }

        public void setStopValue(BigDecimal stopValue) {
            this.stopValue = stopValue;
        }

        public BigDecimal getLastPrice() {
            return lastPrice;
        }

        public void setLastPrice(BigDecimal lastPrice) {
            this.lastPrice = lastPrice;
        }
 
        @Override
        public String toString() {
            return "ValueAmount{" + "value=" + value + ", amount=" + amount + ", price=" + price + ", stop=" + stopValue + '}';
        }
        
    }
}
