/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.strategy;

import com.mgl.entities.BarEntity;
import com.mgl.entities.ContractEntity;
import com.mgl.strategy.indicator.Mansfield;
import com.mgl.strategy.indicator.exceptions.MansfieldException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Michael G. Langer
 */
public class HighStrategy {
    
    /**
     * This strategy...
     * @param contract 
     * @param days to be analyzed
     * @param avgDays the amount of days used for the volume of the closest days.
     * @return
     */
    public HighStrategyData execute(ContractEntity contract, int days, int avgDays) {
        if (contract.getBarCollection() == null || contract.getBarCollection().isEmpty()) {
            System.err.println("Bars empty for "+ contract.getSymbol());
            return null;
        }
        HighStrategyData result = new HighStrategyData(contract);
        Collection<BarEntity> bars = contract.getBarCollection();
                
        BigDecimal high = null;
        BigDecimal low = null;
        /**
         * Moving average of n days
         */
        BigDecimal mvaDays = null;
        Long avgVol = 0l;
        Long avgDaysVol = 0l;
        
        List<BarEntity> brs;
        
        if ( bars.size() > days ) {
            brs = new ArrayList(bars).subList(bars.size()-days, bars.size());
        } else {
            brs = new ArrayList(bars);
        }
        
        for (BarEntity b : brs) {
            avgVol += b.getVolume();
            if (high == null) {
                high = b.getHigh();
            }
            if (low == null) {
                low = b.getLow();
            }
            if (mvaDays != null) {
                mvaDays = mvaDays.add(b.getBclose());
            } else {
                mvaDays = b.getBclose();
            }
            if (b.getBclose().compareTo(high) > 0) {
                high = b.getBclose();
            }
            if (b.getBclose().compareTo(low) < 0) {
                low = b.getBclose();
            }
        }
        
        List<BarEntity> br = new ArrayList(bars).subList(bars.size()-avgDays, bars.size());
        for (BarEntity b : br) {
            avgDaysVol += b.getVolume();
        } 
        
        result.setHighest(high);
        result.setLowest(low);
        result.setAverageVolume((long) avgVol / brs.size());
        mvaDays = mvaDays.divide(new BigDecimal(days));
        result.setDaysAvg(mvaDays);
        result.setAverageDaysVolume(avgDaysVol / avgDays);

        Mansfield m = new Mansfield();
        double dd = 0;
        try {
            dd = m.execute(contract, 50, "SPY");
            dd = m.execute(contract, 50, "MDY");
        } catch (MansfieldException e) {
            e.printStackTrace();
        }
        result.setMansfield(dd);

       if (result.process()) {
           return result;
       }
        
        
        return null;
    }
           
    
}
