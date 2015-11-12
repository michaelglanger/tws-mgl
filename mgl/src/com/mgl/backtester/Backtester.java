/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.backtester;

import com.mgl.entities.BarEntity;
import com.mgl.entities.ContractEntity;
import com.mgl.entities.ControllerFactory;
import com.mgl.io.Contract;
import com.mgl.io.Ctrs;
import com.mgl.io.XHelper;
import com.mgl.strategy.HighStrategy2;
import com.mgl.strategy.HighStrategyData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Michael G. Langer
 */
public class Backtester {

    private static final String CONTRACT_FILE = "contracts.xml";
    private static final String CONTRACT_FILE_MIN = "contracts_minimal.xml";

    public static void main(String[] args) {
        Backtester tester = new Backtester();
        tester.process();
    }

    public void process() {
        Portfolio portfolio = new Portfolio();
        Ctrs ctrs = XHelper.getInstance().getCtrs(CONTRACT_FILE_MIN);
        ControllerFactory controllerFactory = new ControllerFactory();

        ContractEntity spy = controllerFactory.getContractJpaController().findContract("SPY");

        Collection<BarEntity> barCollection = spy.getBarCollection();
        List<BarEntity> barList = new ArrayList<BarEntity>(barCollection);

        Collections.sort(barList, new Comparator<BarEntity>() {
            @Override
            public int compare(BarEntity o1, BarEntity o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        if (barList.size() > 200) {

            HighStrategy2 strategy = new HighStrategy2(controllerFactory);

            for (int i = 200; i < barList.size(); i++) {
                BarEntity be = barList.get(i);
                Date date = be.getDate();

                for (Contract nc : ctrs.getContracts()) {
                    ContractEntity contract = controllerFactory.getContractJpaController().findContract(nc.getSymbol());
                    if (contract != null) {

                        if (!portfolio.contains(nc)) {
                            HighStrategyData data = strategy.execute(contract, 100, 3, date);
                            if (data != null) {
                                float cash = portfolio.getCash().floatValue();
                                
                                int a = Math.round(((cash*0.08) > 8000 ? (int)(cash*0.08) : 8000) / data.getLastClose().floatValue());
                                portfolio.buy(nc, data.getLastClose(), a);
                            }
                        } else {
                            BarEntity bar = controllerFactory.getBarJpaController().findBar(contract, date);
                            if (bar != null) {
                                portfolio.setLastPrice(nc, bar.getBclose());
                                BigDecimal stop = portfolio.getStop(nc);
                                if (bar.getBclose().compareTo(stop) <= 0) {
                                    portfolio.sell(nc, bar.getBclose());
                                } else {
                                    BigDecimal stopValue = bar.getBclose().multiply(new BigDecimal("0.90"));
                                    if (stopValue.compareTo(stop) > 0  && stopValue.compareTo(portfolio.getPrice(nc)) > 0) {
                                        portfolio.setStop(nc, stopValue);
                                    }
                                }
                            }
                           
                        }

                    } else {
                        System.err.println("No contract for symbol: " + nc.getSymbol());
                    }
                }

            }

        }
        System.out.println(portfolio.getCash().toString());

    }

}
