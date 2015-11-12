/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.strategy;

import com.mgl.entities.ContractEntity;
import com.mgl.entities.ControllerFactory;
import com.mgl.io.Contract;
import com.mgl.io.Ctrs;
import com.mgl.io.XHelper;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Michael G. Langer
 */
public class StrategyHigh {

    private PropertyChangeListener tableListener;

    public StrategyHigh() {

    }

    public StrategyHigh(PropertyChangeListener listener) {
        tableListener = listener;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StrategyHigh strategy = new StrategyHigh();
        strategy.process();
    }

    public void process() {
        Ctrs ctrs = XHelper.getInstance().getCtrs("contracts.xml");

        ControllerFactory controllerFactory = new ControllerFactory();

        HighStrategy strategy = new HighStrategy();

        List<HighStrategyData> dataList = new ArrayList<HighStrategyData>();
        for (Contract nc : ctrs.getContracts()) {
            ContractEntity contract = controllerFactory.getContractJpaController().findContract(nc.getSymbol());
            if (contract != null) {
                HighStrategyData data = strategy.execute(contract, 200, 3);
                if (data != null) {
                    dataList.add(data);
                }
            } else {
                System.err.println("No contract for symbol: " + nc.getSymbol());
            }
        }

        String[][] data = new String[dataList.size()][8];

        Collections.sort(dataList, new Comparator<HighStrategyData>() {

            @Override
            public int compare(HighStrategyData o1, HighStrategyData o2) {
                return o2.getAverageVolumenDelta().compareTo(o1.getAverageVolumenDelta());
            }

        });

        for (int i = 0; i < dataList.size(); i++) {
            String symbol = dataList.get(i).getContract().getSymbol();

            data[i] = new String[]{"" + (i + 1),
                symbol,
                dataList.get(i).getHighest().toString(),
                dataList.get(i).getLowest().toString(),
                dataList.get(i).getLastClose().toString(),
                String.format("%.2f", dataList.get(i).getMansfield()),
                dataList.get(i).getDaysAvg().toString(),
                dataList.get(i).getAverageVolume().toString(),
                dataList.get(i).getAverageDaysVolume().toString(),
                dataList.get(i).getAverageVolumenDelta().toString()};
        }

        tableListener.propertyChange(new PropertyChangeEvent(this, "StrategyTable", null, data));

//            HighStrategyTable table = new HighStrategyTable();
//            table.print(dataList);
    }

}
