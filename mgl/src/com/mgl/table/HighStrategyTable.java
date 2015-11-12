/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.table;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.ASCIITableHeader;
import com.mgl.strategy.HighStrategyData;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Michael G. Langer
 */
public class HighStrategyTable {
    
    public void print(List<HighStrategyData> dataList) {
        
        ASCIITableHeader[] headerObjs = {
                        new ASCIITableHeader("#", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("Symbol", ASCIITable.ALIGN_LEFT),
                        new ASCIITableHeader("High", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("Low", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("Close", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("MFLD", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("MVA200", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("AvgVol", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("DaysAvgVol", ASCIITable.ALIGN_RIGHT),
                        new ASCIITableHeader("VolDelta", ASCIITable.ALIGN_RIGHT)
            };
           
        String[][] data = new String[dataList.size()][8];
        
        Collections.sort(dataList, new Comparator<HighStrategyData>() {

            @Override
            public int compare(HighStrategyData o1, HighStrategyData o2) {
                return o2.getAverageVolumenDelta().compareTo(o1.getAverageVolumenDelta());
            }
            
        });
        
        for (int i=0; i < dataList.size(); i++) {
            String symbol = dataList.get(i).getContract().getSymbol();
                                    
            data[i] = new String[] {""+(i+1), 
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
        
        ASCIITable.getInstance().printTable(headerObjs, data);
    }
    
    
}
