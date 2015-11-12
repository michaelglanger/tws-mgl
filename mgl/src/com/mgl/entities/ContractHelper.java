/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.entities;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Michael G. Langer
 */
public class ContractHelper {
    
    /**
     * This method return the latest bar for the given contract.
     * @param contract
     * @return the latest bar 
     */
    public BarEntity getLatestBar(ContractEntity contract) {
        BarEntity result = null;
        Collection<BarEntity> bars = contract.getBarCollection();
       
        if (!bars.isEmpty()) {
            for (BarEntity b : bars) {
                if (result != null) {
                    if ( result.getDate().before(b.getDate())) {
                        result = b;
                    }
                } else {
                    result = b;
                }
            }
        }
        return result;
    }
    
    public BarEntity getLatestBar(ContractEntity contract, BarJpaController bjc) {
        return bjc.findLastBar(contract);
    }
    
    
}
