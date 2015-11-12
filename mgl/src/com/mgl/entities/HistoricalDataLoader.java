/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.entities;

import com.ib.controller.ApiController;
import com.ib.controller.Bar;
import com.ib.controller.NewContract;
import com.ib.controller.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael G. Langer
 */
public class HistoricalDataLoader implements ApiController.IHistoricalDataHandler, Callable<Void> {

    private NewContract nc;
    private ApiController apiController;
    private Types.BarSize barSize = Types.BarSize._1_day;
    private Types.WhatToShow whatToShow = Types.WhatToShow.TRADES;
    private boolean rthOnly = false;
    private Types.DurationUnit durationUnit;
    private int duration;
    private String endDateTime;
    private List<BarEntity> bars = new ArrayList<BarEntity>();
    private BarJpaController bjc;
    private ContractEntity contract;
    
    public HistoricalDataLoader(NewContract nc, ContractEntity c, ApiController apiController, String endDateTime, int duration, BarJpaController bjc) {
        this(nc, c, apiController, endDateTime, duration, Types.DurationUnit.DAY, bjc);
    }
    
    public HistoricalDataLoader(NewContract nc, ContractEntity c, ApiController apiController, String endDateTime, int duration, Types.DurationUnit durationUnit, BarJpaController bjc) {
        this.nc = nc;
        this.apiController = apiController;
        this.duration = duration;
        this.endDateTime = endDateTime;
        this.durationUnit = durationUnit;
        this.bjc = bjc;
        this.contract = c;
    }
    
    @Override
    public void historicalData(Bar bar, boolean hasGaps) {
        System.err.println("B: " + bar.toString());
        bars.add(new BarEntity(bar));
    }

    @Override
    public void historicalDataEnd() {
        for (BarEntity be : bars) {
            try {
                if (bjc.findBar(contract, be.getDate()) == null) {
                    contract.getBarCollection().add(be);
                    be.setContract(contract);
                    bjc.create(be);
                }
            } catch (Exception ex) {
                Logger.getLogger(HistoricalDataLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public Void call() throws Exception {
        apiController.reqHistoricalData(nc, endDateTime + " 12:00:00", duration, durationUnit, barSize, whatToShow, rthOnly, this);
        return null;
    }
    
}
