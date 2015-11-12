/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.strategy;

import com.mgl.entities.BarEntity;
import com.mgl.entities.ContractEntity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author Michael G. Langer
 */
public class HighStrategyData {
    
    private ContractEntity contract;
    private BigDecimal highest;
    private BigDecimal lowest;
    private BigDecimal daysAvg;
    private BigDecimal lastClose;
    private Long averageVolume;
    /* average volume for the n last days*/
    private Long averageDaysVolume;
    private BigDecimal averageVolumenDelta;
    private double mansfield;

    
    public HighStrategyData(ContractEntity contract) {
        this.contract = contract;
        
        BarEntity bar = (BarEntity) contract.getBarCollection().toArray()[contract.getBarCollection().size()-1];
        lastClose = bar.getBclose();
    }
    
    public HighStrategyData(ContractEntity contract, List<BarEntity> b) {
        this.contract = contract;
        
        BarEntity bar = (BarEntity) b.toArray()[b.size()-1];
        lastClose = bar.getBclose();
    }

    public ContractEntity getContract() {
        return contract;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }

    public BigDecimal getHighest() {
        return highest;
    }

    public void setHighest(BigDecimal highest) {
        this.highest = highest;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public void setLowest(BigDecimal lowest) {
        this.lowest = lowest;
    }

    public Long getAverageVolume() {
        return averageVolume;
    }

    public void setAverageVolume(Long averageVolume) {
        this.averageVolume = averageVolume;
    }

    public BigDecimal getDaysAvg() {
        return daysAvg;
    }

    public void setDaysAvg(BigDecimal daysAvg) {
        this.daysAvg = daysAvg;
    }

    public Long getAverageDaysVolume() {
        return averageDaysVolume;
    }

    public void setAverageDaysVolume(Long averageDaysVolume) {
        this.averageDaysVolume = averageDaysVolume;
    }

    public BigDecimal getLastClose() {
        return lastClose;
    }

    public void setLastClose(BigDecimal lastClose) {
        this.lastClose = lastClose;
    }

    public BigDecimal getAverageVolumenDelta() {
        return averageVolumenDelta;
    }

    public double getMansfield() {
        return mansfield;
    }

    public void setMansfield(double mansfield) {
        this.mansfield = mansfield;
    }

    public void setAverageVolumenDelta(BigDecimal averageVolumenDelta) {
        this.averageVolumenDelta = averageVolumenDelta;
    }
    
    public boolean process() {
        // process the average volume delta.
        averageVolumenDelta = new BigDecimal(((float)averageDaysVolume / (float)averageVolume));
        averageVolumenDelta = averageVolumenDelta.setScale(4, RoundingMode.UP);
        
        return ( (lastClose.doubleValue() > (highest.doubleValue() * (1-0.03))) &&  
                 (averageDaysVolume > averageVolume) &&
                 (lastClose.doubleValue() > daysAvg.doubleValue())
                 && (mansfield > 0 )
        );
    }
    
    public boolean process2() {
        // process the average volume delta.
        averageVolumenDelta = new BigDecimal(((float)averageDaysVolume / (float)averageVolume));
        averageVolumenDelta = averageVolumenDelta.setScale(4, RoundingMode.UP);
        double lwst = lowest.doubleValue();
        double hst = highest.doubleValue();
        double lst = lastClose.doubleValue();
        double d = lwst + ((hst - lwst) * 0.10);
        return ( (lastClose.doubleValue() >= d) &&  
                 (averageDaysVolume >= (averageVolume * (1.3))) &&
                 (lastClose.doubleValue() >= daysAvg.doubleValue()) && 
                 (mansfield > 1.4)
        );
    }
    
    public boolean process3() {
        // process the average volume delta.
        averageVolumenDelta = new BigDecimal(((float)averageDaysVolume / (float)averageVolume));
        averageVolumenDelta = averageVolumenDelta.setScale(4, RoundingMode.UP);
        
        return ( (lastClose.doubleValue() >= (highest.doubleValue())) &&  
                 (averageDaysVolume >= (averageVolume * (1.3))) &&
                 (lastClose.doubleValue() >= daysAvg.doubleValue()) && 
                 (mansfield > 1.4)
        );
    }
    
    @Override
    public String toString() {
        return "HighStrategyData{" + "contract=" + contract + ", highest=" + highest 
                + ", lowest=" + lowest + ", averageVolume=" + averageVolume + ", close=" 
                + lastClose +", daysAvg=" + daysAvg + ", avgDaysVol=" + averageDaysVolume 
                + ", Mansfield=" + mansfield +"}";
    }
            
}
