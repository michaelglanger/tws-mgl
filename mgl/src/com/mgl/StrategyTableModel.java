/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michael G. Langer
 */
public class StrategyTableModel extends DefaultTableModel {

    public StrategyTableModel(String[][] ss) {
        super(ss, new String[]{"#", "Symbol", "High", "Low", "Close", "MFLD", "MVA200", "AvgVol", "DaysAvgVol", "VolDelta"});
    }

    Class[] types = new Class[]{
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
    };
    boolean[] canEdit = new boolean[]{
        false, false, false, false, false, false, false, false, false, false
    };

    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

}
