package com.mgl.old;

import java.util.concurrent.Callable;

import javax.swing.SwingWorker;

import com.ib.controller.*;
import com.ib.controller.ApiController.IHistoricalDataHandler;
import com.ib.controller.Types.BarSize;
import com.ib.controller.Types.DurationUnit;
import com.ib.controller.Types.WhatToShow;
import com.mgl.io.ConnectionHandler;
import com.mgl.io.ConnectionHandler;
import com.mgl.Data;
import com.mgl.Data;
import com.mgl.DataManager;
import com.mgl.DataManager;
import com.mgl.XProcess;

public class MyIHistoricalDataHandler implements IHistoricalDataHandler, Callable<Void>, ApiController.ITopMktDataHandler {

    private NewContract nc;

    String endDateTime; // = "20141007 12:00:00";
    int duration = 1;
    DurationUnit durationUnit = DurationUnit.YEAR;
    BarSize barSize = BarSize._1_day;
    WhatToShow whatToShow = WhatToShow.TRADES;
    boolean rthOnly = false;
    ConnectionHandler cHandler;
    SwingWorker<Void, Void> sw;
    ApiController apiController;
    private boolean historical = true;

    public MyIHistoricalDataHandler(NewContract nc, ApiController apiController, String endDateTime, boolean historical) {
        this.nc = nc;
        this.apiController = apiController;
        this.endDateTime = endDateTime;
        this.historical = historical;
    }

    @Override
    public void historicalData(final Bar bar, final boolean hasGaps) {
        Data data = DataManager.getInstance().getData(nc.symbol());
        if (data != null) {
            data.addBar(bar);
        } else {
            Data d = new Data();
            d.addBar(bar);
            DataManager.getInstance().getData().put(nc.symbol(), d);
        }
    }

    @Override
    public void historicalDataEnd() {
        Data data = DataManager.getInstance().getData(nc.symbol());
        // move this to another part
        XProcess process = new XProcess();
        process.processHighLow(data);

        apiController.reqTopMktData(nc, "", true, this);
    }

    @Override
    public Void call() throws Exception {
        if (historical) {
            apiController.reqHistoricalData(nc, endDateTime + " 12:00:00", duration, durationUnit, barSize, whatToShow, rthOnly, this);
        } else {
            apiController.reqTopMktData(nc, "", true, this);
        }
        return null;
    }

    @Override
    public void tickPrice(NewTickType tickType, double price, int canAutoExecute) {
        Data data = DataManager.getInstance().getData(nc.symbol());
        switch( tickType) {
            case BID:
                data.setBid(price);
                break;
            case ASK:
                data.setAsk(price);
                break;
            case LAST:
                data.setLast(price);
                break;
            case CLOSE:
                data.setClose(price);
                break;
        }
    }

    @Override
    public void tickSize(NewTickType tickType, int size) {
        Data data = DataManager.getInstance().getData(nc.symbol());
        switch( tickType) {
            case BID_SIZE:
                data.setBidSize(size);
                break;
            case ASK_SIZE:
                data.setAskSize(size);
                break;
            case VOLUME:
                data.setVolume(size);
                break;
        }
    }

    @Override
    public void tickString(NewTickType tickType, String value) {
        Data data = DataManager.getInstance().getData(nc.symbol());
        switch( tickType) {
            case LAST_TIMESTAMP:
                data.setLastTimestamp(Long.parseLong( value) * 1000);
                break;
        }
    }

    @Override
    public void tickSnapshotEnd() {
        Data data = DataManager.getInstance().getData(nc.symbol());
        Serializer.getInstance().put(nc.symbol()+endDateTime, data);
    }

    @Override
    public void marketDataType(Types.MktDataType marketDataType) {
        System.out.println(marketDataType.toString());
    }






}
