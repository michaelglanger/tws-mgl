/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.updater;

import com.ib.controller.NewContract;
import com.ib.controller.Types;
import com.mgl.entities.*;
import com.mgl.io.ConnectionHandler;
import com.mgl.io.TextPaneLogger;
import com.mgl.io.XHelper;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael G. Langer
 */
public class Updater2 {

    private static final String CONTRACT_FILE = "contracts.xml";
    private static final String CONTRACT_FILE_MIN = "contracts_minimal.xml";
    private static final String YMD = "yyyyMMdd";
    private static final int _10s = 10000;
    private static final int _1s = 1000;
    
    private PropertyChangeListener progressBarListener;

    public Updater2() {
        
    }
    
    public Updater2(PropertyChangeListener listener) {
        progressBarListener = listener;
    }
    
    public static void main(String[] args) {
        Updater2 updater = new Updater2();
        updater.process();
    }

    public void process() {

        List<NewContract> nContractList = XHelper.getInstance().fromFileNameToNCList(CONTRACT_FILE);
        ControllerFactory controllerFactory = new ControllerFactory();
        ContractHelper contractHelper = new ContractHelper();

        TextPaneLogger logger = new TextPaneLogger(progressBarListener);
        
        try (ConnectionHandler cHandler = new ConnectionHandler(logger)) {
            cHandler.connect();
            progressBarListener.propertyChange( new PropertyChangeEvent(this, "UpdaterLog", null, nContractList.size() + " Contracts to be processed."));

            int counter = 0;
            for (NewContract nc : nContractList) {

                ContractEntity contractEntity = controllerFactory.getContractJpaController().findContract( nc.symbol() );
                if (contractEntity == null) {
                    contractEntity = new ContractEntity(nc.symbol(), nc.exchange(), nc.currency());
                    controllerFactory.getContractJpaController().create(contractEntity);
                }

                BarEntity latestBar = contractHelper.getLatestBar(contractEntity, controllerFactory.getBarJpaController());
                SimpleDateFormat sdf = new SimpleDateFormat(YMD); // move this 2 lines
                String endDateTime = sdf.format(new DateTime().toDate());

                counter++;
                HistoricalDataLoaderWorker handler = null;
                if (latestBar == null) {
                    progressBarListener.propertyChange( new PropertyChangeEvent(this, "UpdaterLog", null, "Processing all " + counter + " of " + nContractList.size() + " Contract: " + contractEntity.getSymbol()));
                    handler = new HistoricalDataLoaderWorker(nc, contractEntity, cHandler.getController(), endDateTime, 5, Types.DurationUnit.YEAR, controllerFactory.getBarJpaController(), logger);
                } else {
                    DateTime endDate = new DateTime();
                    DateTime startDate = new DateTime(latestBar.getDate().getTime());
                    
                    int days = 0;
                    DateTime nextDay = startDate.plusDays(1);
                    while (nextDay.isBefore(endDate))  {
                        DateTime.Property p = nextDay.dayOfWeek(); 
                        if (p.get()<6) {
                            days++;
                        }
                        nextDay = nextDay.plusDays(1);
                    }

                    if (days > 1) { // TODO if after market close it can be 0.
                        progressBarListener.propertyChange( new PropertyChangeEvent(this, "UpdaterLog", null, "Processing " + counter + " of " + nContractList.size() + " Contract: " + contractEntity.getSymbol() + " days: " + days));
                        handler = new HistoricalDataLoaderWorker(nc, contractEntity, cHandler.getController(), endDateTime, days, controllerFactory.getBarJpaController(), logger);
                    } else {
                        progressBarListener.propertyChange( new PropertyChangeEvent(this, "UpdaterLog", null, "Processing " + counter + " of " + nContractList.size() + " Contract: " + contractEntity.getSymbol() + " no days"));
                    }
                }
                if (progressBarListener != null) {
                    float f = ((counter*1f) / nContractList.size()) * 100f;
                    progressBarListener.propertyChange( new PropertyChangeEvent(this, "UpdaterProgress", null, Math.round(f)));
                }
                if (handler != null) {
                    long bMillis = System.currentTimeMillis();
                    handler.execute();
                    handler.get();
                    handler.end();
                    while ((System.currentTimeMillis() - bMillis) < _10s) {
                        Thread.sleep(_1s);
                    }
                }
            }
            progressBarListener.propertyChange( new PropertyChangeEvent(this, "UpdaterLog", null, "End"));
        } catch (Exception e) {
            Logger.getLogger(Updater2.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
