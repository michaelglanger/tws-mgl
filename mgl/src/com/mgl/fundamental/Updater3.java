/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental;

import com.ib.controller.NewContract;
import com.mgl.entities.*;
import com.mgl.io.ConnectionHandler;
import com.mgl.io.XHelper;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mgl.io.CLLogger;

/**
 * @author Michael G. Langer
 */
public class Updater3 {

    private static final String CONTRACT_FILE = "contracts.xml";
    private static final String CONTRACT_FILE_MIN = "contracts_minimal.xml";
    private static final String YMD = "yyyyMMdd";
    private static final int _10s = 10000;
    private static final int _1s = 1000;

    private PropertyChangeListener progressBarListener;

    public Updater3() {

    }

    public Updater3(PropertyChangeListener listener) {
        progressBarListener = listener;
    }
    
    public static void main(String[] args) {
        Updater3 updater = new Updater3();
        updater.process();
    }

    public void process() {

        List<NewContract> nContractList = XHelper.getInstance().fromFileNameToNCList(CONTRACT_FILE_MIN);
        ControllerFactory controllerFactory = new ControllerFactory();
        ContractHelper contractHelper = new ContractHelper();

        CLLogger logger = new CLLogger();//progressBarListener);
        
        try (ConnectionHandler cHandler = new ConnectionHandler(logger)) {
            cHandler.connect();
//            progressBarListener.propertyChange(new PropertyChangeEvent(this, "UpdaterLog", null, nContractList.size() + " Contracts to be processed."));

            for (NewContract nc : nContractList) {
                FundamentalDataLoaderWorker handler = new FundamentalDataLoaderWorker(nc, cHandler.getController());

                if (handler != null) {
                    long bMillis = System.currentTimeMillis();
                    handler.execute();
                    handler.get();
                    while ((System.currentTimeMillis() - bMillis) < _10s) {
                        Thread.sleep(_1s);
                    }
                }
            }
//            progressBarListener.propertyChange( new PropertyChangeEvent(this, "UpdaterLog", null, "End"));
        } catch (Exception e) {
            Logger.getLogger(Updater3.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
