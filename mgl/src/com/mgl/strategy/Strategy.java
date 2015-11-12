package com.mgl.strategy;

import com.ib.controller.NewContract;
import com.mgl.Data;
import com.mgl.DataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael G. Langer on 4/11/2014.
 */
public class Strategy {

    private float frontierLevel = 0.95f;
    private float frontierLevelVol = 0.70f;

    public List<NewContract> process( List<NewContract> nContractList ) {
        List<NewContract> result = new ArrayList<NewContract>();

        for (NewContract nc : nContractList) {
            Data dd = DataManager.getInstance().getData(nc.symbol());

//            boolean above50Mva = dd.getLast() > dd.getMva50();
//            boolean frontier = dd.getLast() > (dd.getHigh() * frontierLevel);
//            boolean averageLimits = dd.getAvg5DVolume() > (dd.getAvgVolume() * frontierLevelVol);
//
//            if (above50Mva && frontier && averageLimits) {
//                result.add(nc);
//            }

        }
        return result;
    }
}
