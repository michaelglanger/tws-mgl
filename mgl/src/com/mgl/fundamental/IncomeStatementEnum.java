/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental;

import com.mgl.fundamental.entities.IncomeStatement;

/**
 *
 * @author Michael Langer
 */
public enum IncomeStatementEnum {

    SREV("Revenue"),
    SORE("Other Revenue, Total"),
    RTLR("Total Revenue"),
    SCOR("Cost of Revenue, Total"),
    SGRP("Gross Profit"),
    SSGA("Selling/General/Admin. Expenses, Total"),
    ERAD("Research &amp; Development"),
    SDPR("Depreciation/Amortization"),
    SINN("Interest Exp.(Inc.),Net-Operating, Total"),
    SUIE("Unusual Expense (Income)"),
    SOOE("Other Operating Expenses, Total"),
    ETOE("Total Operating Expense"),
    SOPI("Operating Income"),
    SNIN("Interest Inc.(Exp.),Net-Non-Op., Total"),
    NGLA("Gain (Loss) on Sale of Assets"),
    SONT("Other, Net"),
    EIBT("Net Income Before Taxes"),
    TTAX("Provision for Income Taxes"),
    TIAT("Net Income After Taxes"),
    CMIN("Minority Interest"),
    CEIA("Equity In Affiliates"),
    CGAP("U.S. GAAP Adjustment"),
    NIBX("Net Income Before Extra. Items"),
    STXI("Total Extraordinary Items"),
    NINC("Net Income"),
    SANI("Total Adjustments to Net Income"),
    CIAC("Income Available to Com Excl ExtraOrd"),
    XNIC("Income Available to Com Incl ExtraOrd"),
    SDAJ("Dilution Adjustment"),
    SDNI("Diluted Net Income"),
    SDWS("Diluted Weighted Average Shares"),
    SDBF("Diluted EPS Excluding ExtraOrd Items"),
    DDPS1("DPS - Common Stock Primary Issue"),
    VDES("Diluted Normalized EPS");
//    {
//        @Override
//        public void method(IncomeStatement inc) {
//            System.out.println("second enum constant behavior!");
//        }
//    };

    private final String name;

    private IncomeStatementEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    /* Returns the text */
    public String toString() {
       return this.name;
    }

//    public abstract void method(IncomeStatement inc);
    
}
