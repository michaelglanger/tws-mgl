/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.entities;

import com.ib.controller.ApiConnection.ILogger;
import com.ib.controller.ApiController;
import com.ib.controller.Bar;
import com.ib.controller.NewContract;
import com.ib.controller.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Michael G. Langer
 */
public class HistoricalDataLoaderWorker extends SwingWorker implements ApiController.IHistoricalDataHandler {

    private static Logger logger = Logger.getLogger(HistoricalDataLoaderWorker.class.getName());
    
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
    private boolean finished = false;
    private ILogger log;
    
    public HistoricalDataLoaderWorker(NewContract nc, ContractEntity c, ApiController apiController, String endDateTime, int duration, BarJpaController bjc, ILogger logger) {
        this(nc, c, apiController, endDateTime, duration, Types.DurationUnit.DAY, bjc, logger);
    }
    
    public HistoricalDataLoaderWorker(NewContract nc, ContractEntity c, ApiController apiController, String endDateTime, int duration, Types.DurationUnit durationUnit, BarJpaController bjc, ILogger logger) {
        this.nc = nc;
        this.apiController = apiController;
        this.duration = duration;
        this.endDateTime = endDateTime;
        this.durationUnit = durationUnit;
        this.bjc = bjc;
        this.contract = c;
        this.log = logger;
    }
    
    @Override
    public void historicalData(Bar bar, boolean hasGaps) {
        if (log != null) {
            log.log(contract.getSymbol() + ": " + bar.toString());
        }
        try {
            BarEntity be = new BarEntity(bar);
            
            if (bjc.findBar(contract, be.getDate()) == null) {
                contract.getBarCollection().add(be);
                be.setContract(contract);
                bjc.create(be);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void historicalDataEnd() {
        finished = true;  
    }

  
    @Override
    protected Boolean doInBackground() throws Exception {
        finished = false;
        apiController.reqHistoricalData(nc, endDateTime + " 12:00:00", duration, durationUnit, barSize, whatToShow, rthOnly, this);
        int maxruns = 0;
        while (finished == false) {
            Thread.sleep(1000);
            if (maxruns >= 300) {
                if (log != null) {
                    log.log("Timeout " + contract.getSymbol() + " duration: " + duration + " duration unit: " + durationUnit.name());
                }
                return Boolean.FALSE;
            }
            maxruns++;
        }
        return Boolean.TRUE;
    }
    
    
    public void end() {
        apiController.cancelHistoricalData(this);
    }
}

