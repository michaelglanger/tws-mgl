package com.mgl;

import java.util.List;

import com.ib.controller.Bar;
import com.mgl.Data;

public class XProcess {
	
	public void processHighLow(Data data) {
		List<Bar> bars = data.getBars();
		
		data.setHigh(bars.get(0).high());
		data.setLow(bars.get(0).low());

        long vol = 0;

		for (Bar bar : bars) {
            vol += bar.volume();
			if (bar == null || data == null) {
				int i = 0;
			}
			if (bar.high() > data.getHigh()) {
				data.setHigh(bar.high());
			}
			if (bar.low() < data.getLow()) {
				data.setLow(bar.low());
			}
		}

        data.setAvgVolume((int) vol/bars.size());

        if (bars.size()>6) {
            List<Bar> bars5D = bars.subList(bars.size() - 6, bars.size() - 1);
            long vol5D = 0;
            for (Bar b5 : bars5D) {
                vol5D += b5.volume();
            }
            data.setAvg5DVolume((int) vol5D / 5);
        }

        if (bars.size() >51) {
            List<Bar> bars50D = bars.subList(bars.size() - 51, bars.size() - 1);
            double last50 = 0;
            for(Bar b50 : bars50D) {
                last50 += b50.close();
            }
            data.setMva50(last50/50);
        }
	}

}
