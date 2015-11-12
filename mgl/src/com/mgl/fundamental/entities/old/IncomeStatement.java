/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.entities.old;

/**
 *
 * @author Michael Langer
 */
public class IncomeStatement extends Statement {

//   <mapItem coaItem="SREV" statementType="INC" lineID="100" precision="1">Revenue</mapItem>
    private int revenue;
           
//    <mapItem coaItem="SORE" statementType="INC" lineID="300" precision="1">Other Revenue, Total</mapItem>
    private int otherRevenueTotal;
    
//    <mapItem coaItem="RTLR" statementType="INC" lineID="310" precision="1">Total Revenue</mapItem>
    private int totalRevenue;
    
//    <mapItem coaItem="SCOR" statementType="INC" lineID="360" precision="1">Cost of Revenue, Total</mapItem>
    private int costOfRevenueTotal;
    
//    <mapItem coaItem="SGRP" statementType="INC" lineID="370" precision="1">Gross Profit</mapItem>
    private int grossProfit;
    
//    <mapItem coaItem="SSGA" statementType="INC" lineID="550" precision="1">Selling/General/Admin. Expenses, Total</mapItem>
    private int sellingGeneralAdminExpensesTotal;
    
//    <mapItem coaItem="ERAD" statementType="INC" lineID="560" precision="1">Research &amp; Development</mapItem>
    private int researchAndDevelopment;
    
//    <mapItem coaItem="SDPR" statementType="INC" lineID="600" precision="1">Depreciation/Amortization</mapItem>
    private int depreciationAmortization;
    
//    <mapItem coaItem="SINN" statementType="INC" lineID="671" precision="1">Interest Exp.(Inc.),Net-Operating, Total</mapItem>
    private int interestExpIncNetOperatingTotal;
    
//    <mapItem coaItem="SUIE" statementType="INC" lineID="740" precision="1">Unusual Expense (Income)</mapItem>
    private int unusualExpensesIncome;
    
//    <mapItem coaItem="SOOE" statementType="INC" lineID="820" precision="1">Other Operating Expenses, Total</mapItem>
    private int otherOperatingExpensesTotal;
    
//    <mapItem coaItem="ETOE" statementType="INC" lineID="830" precision="1">Total Operating Expense</mapItem>
    private int totalOperatingExpenses;
    
//    <mapItem coaItem="SOPI" statementType="INC" lineID="840" precision="1">Operating Income</mapItem>
    private int operatingIncome;
    
//    <mapItem coaItem="SNIN" statementType="INC" lineID="911" precision="1">Interest Inc.(Exp.),Net-Non-Op., Total</mapItem>
    private int interestIncExpNetNonOpTotal;
    
//    <mapItem coaItem="NGLA" statementType="INC" lineID="920" precision="1">Gain (Loss) on Sale of Assets</mapItem>
    private int gainLossOnSaleOfAssets;
    
//    <mapItem coaItem="SONT" statementType="INC" lineID="1270" precision="1">Other, Net</mapItem>
    private int otherNet;
    
//    <mapItem coaItem="EIBT" statementType="INC" lineID="1280" precision="1">Net Income Before Taxes</mapItem>
    private int netIncomeBeforeTaxes;
            
//    <mapItem coaItem="TTAX" statementType="INC" lineID="1290" precision="1">Provision for Income Taxes</mapItem>
    private int provisionForIncomeTaxes;
    
//    <mapItem coaItem="TIAT" statementType="INC" lineID="1300" precision="1">Net Income After Taxes</mapItem>
    private int netIncomeAfterTaxes;
    
//    <mapItem coaItem="CMIN" statementType="INC" lineID="1310" precision="1">Minority Interest</mapItem>
    private int minorityInterest;
    
//    <mapItem coaItem="CEIA" statementType="INC" lineID="1320" precision="1">Equity In Affiliates</mapItem>
    private int equityInAffiliates;
    
//    <mapItem coaItem="CGAP" statementType="INC" lineID="1330" precision="1">U.S. GAAP Adjustment</mapItem>
    private int usGaapAdjustment;
    
//    <mapItem coaItem="NIBX" statementType="INC" lineID="1340" precision="1">Net Income Before Extra. Items</mapItem>
    private int netIncomeBeforeExtraItems;
    
//    <mapItem coaItem="STXI" statementType="INC" lineID="1390" precision="1">Total Extraordinary Items</mapItem>
    private int totalExtraordinaryItems;
    
//    <mapItem coaItem="NINC" statementType="INC" lineID="1400" precision="1">Net Income</mapItem>
    private int netIncome;
    
//    <mapItem coaItem="SANI" statementType="INC" lineID="1460" precision="1">Total Adjustments to Net Income</mapItem>
    private int totalAdjustmentsToNetIncome;
    
//    <mapItem coaItem="CIAC" statementType="INC" lineID="1470" precision="1">Income Available to Com Excl ExtraOrd</mapItem>
    private int incomeAvailableToComExclExtraOrd;
    
//    <mapItem coaItem="XNIC" statementType="INC" lineID="1480" precision="1">Income Available to Com Incl ExtraOrd</mapItem>
    private int incomeAvailableToComInclExtraOrd;
    
//    <mapItem coaItem="SDAJ" statementType="INC" lineID="1520" precision="1">Dilution Adjustment</mapItem>
    private int dilutionAdjustment;
    
//    <mapItem coaItem="SDNI" statementType="INC" lineID="1530" precision="1">Diluted Net Income</mapItem>
    private int dilutedNetIncome;
    
//    <mapItem coaItem="SDWS" statementType="INC" lineID="1540" precision="2">Diluted Weighted Average Shares</mapItem>
    private int dilutedWeightedAverageShares;
    
//    <mapItem coaItem="SDBF" statementType="INC" lineID="1550" precision="3">Diluted EPS Excluding ExtraOrd Items</mapItem>
    private int dilutedEpsExcludingExtraOrdItems;
    
//    <mapItem coaItem="DDPS1" statementType="INC" lineID="1570" precision="3">DPS - Common Stock Primary Issue</mapItem>
    private int dpsCommonStockPrimaryIssue;
    
//    <mapItem coaItem="VDES" statementType="INC" lineID="1770" precision="3">Diluted Normalized EPS</mapItem>
    private int dilutedNormalizedEps;
    
    public IncomeStatement(Company aCompany) {
        super(aCompany);
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getOtherRevenueTotal() {
        return otherRevenueTotal;
    }

    public void setOtherRevenueTotal(int otherRevenueTotal) {
        this.otherRevenueTotal = otherRevenueTotal;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getCostOfRevenueTotal() {
        return costOfRevenueTotal;
    }

    public void setCostOfRevenueTotal(int costOfRevenueTotal) {
        this.costOfRevenueTotal = costOfRevenueTotal;
    }

    public int getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(int grossProfit) {
        this.grossProfit = grossProfit;
    }

    public int getSellingGeneralAdminExpensesTotal() {
        return sellingGeneralAdminExpensesTotal;
    }

    public void setSellingGeneralAdminExpensesTotal(int sellingGeneralAdminExpensesTotal) {
        this.sellingGeneralAdminExpensesTotal = sellingGeneralAdminExpensesTotal;
    }

    public int getResearchAndDevelopment() {
        return researchAndDevelopment;
    }

    public void setResearchAndDevelopment(int researchAndDevelopment) {
        this.researchAndDevelopment = researchAndDevelopment;
    }

    public int getDepreciationAmortization() {
        return depreciationAmortization;
    }

    public void setDepreciationAmortization(int depreciationAmortization) {
        this.depreciationAmortization = depreciationAmortization;
    }

    public int getInterestExpIncNetOperatingTotal() {
        return interestExpIncNetOperatingTotal;
    }

    public void setInterestExpIncNetOperatingTotal(int interestExpIncNetOperatingTotal) {
        this.interestExpIncNetOperatingTotal = interestExpIncNetOperatingTotal;
    }

    public int getUnusualExpensesIncome() {
        return unusualExpensesIncome;
    }

    public void setUnusualExpensesIncome(int unusualExpensesIncome) {
        this.unusualExpensesIncome = unusualExpensesIncome;
    }

    public int getOtherOperatingExpensesTotal() {
        return otherOperatingExpensesTotal;
    }

    public void setOtherOperatingExpensesTotal(int otherOperatingExpensesTotal) {
        this.otherOperatingExpensesTotal = otherOperatingExpensesTotal;
    }

    public int getTotalOperatingExpenses() {
        return totalOperatingExpenses;
    }

    public void setTotalOperatingExpenses(int totalOperatingExpenses) {
        this.totalOperatingExpenses = totalOperatingExpenses;
    }

    public int getOperatingIncome() {
        return operatingIncome;
    }

    public void setOperatingIncome(int operatingIncome) {
        this.operatingIncome = operatingIncome;
    }

    public int getInterestIncExpNetNonOpTotal() {
        return interestIncExpNetNonOpTotal;
    }

    public void setInterestIncExpNetNonOpTotal(int interestIncExpNetNonOpTotal) {
        this.interestIncExpNetNonOpTotal = interestIncExpNetNonOpTotal;
    }

    public int getGainLossOnSaleOfAssets() {
        return gainLossOnSaleOfAssets;
    }

    public void setGainLossOnSaleOfAssets(int gainLossOnSaleOfAssets) {
        this.gainLossOnSaleOfAssets = gainLossOnSaleOfAssets;
    }

    public int getOtherNet() {
        return otherNet;
    }

    public void setOtherNet(int otherNet) {
        this.otherNet = otherNet;
    }

    public int getNetIncomeBeforeTaxes() {
        return netIncomeBeforeTaxes;
    }

    public void setNetIncomeBeforeTaxes(int netIncomeBeforeTaxes) {
        this.netIncomeBeforeTaxes = netIncomeBeforeTaxes;
    }

    public int getProvisionForIncomeTaxes() {
        return provisionForIncomeTaxes;
    }

    public void setProvisionForIncomeTaxes(int provisionForIncomeTaxes) {
        this.provisionForIncomeTaxes = provisionForIncomeTaxes;
    }

    public int getNetIncomeAfterTaxes() {
        return netIncomeAfterTaxes;
    }

    public void setNetIncomeAfterTaxes(int netIncomeAfterTaxes) {
        this.netIncomeAfterTaxes = netIncomeAfterTaxes;
    }

    public int getMinorityInterest() {
        return minorityInterest;
    }

    public void setMinorityInterest(int minorityInterest) {
        this.minorityInterest = minorityInterest;
    }

    public int getEquityInAffiliates() {
        return equityInAffiliates;
    }

    public void setEquityInAffiliates(int equityInAffiliates) {
        this.equityInAffiliates = equityInAffiliates;
    }

    public int getUsGaapAdjustment() {
        return usGaapAdjustment;
    }

    public void setUsGaapAdjustment(int usGaapAdjustment) {
        this.usGaapAdjustment = usGaapAdjustment;
    }

    public int getNetIncomeBeforeExtraItems() {
        return netIncomeBeforeExtraItems;
    }

    public void setNetIncomeBeforeExtraItems(int netIncomeBeforeExtraItems) {
        this.netIncomeBeforeExtraItems = netIncomeBeforeExtraItems;
    }

    public int getTotalExtraordinaryItems() {
        return totalExtraordinaryItems;
    }

    public void setTotalExtraordinaryItems(int totalExtraordinaryItems) {
        this.totalExtraordinaryItems = totalExtraordinaryItems;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(int netIncome) {
        this.netIncome = netIncome;
    }

    public int getTotalAdjustmentsToNetIncome() {
        return totalAdjustmentsToNetIncome;
    }

    public void setTotalAdjustmentsToNetIncome(int totalAdjustmentsToNetIncome) {
        this.totalAdjustmentsToNetIncome = totalAdjustmentsToNetIncome;
    }

    public int getIncomeAvailableToComExclExtraOrd() {
        return incomeAvailableToComExclExtraOrd;
    }

    public void setIncomeAvailableToComExclExtraOrd(int incomeAvailableToComExclExtraOrd) {
        this.incomeAvailableToComExclExtraOrd = incomeAvailableToComExclExtraOrd;
    }

    public int getIncomeAvailableToComInclExtraOrd() {
        return incomeAvailableToComInclExtraOrd;
    }

    public void setIncomeAvailableToComInclExtraOrd(int incomeAvailableToComInclExtraOrd) {
        this.incomeAvailableToComInclExtraOrd = incomeAvailableToComInclExtraOrd;
    }

    public int getDilutionAdjustment() {
        return dilutionAdjustment;
    }

    public void setDilutionAdjustment(int dilutionAdjustment) {
        this.dilutionAdjustment = dilutionAdjustment;
    }

    public int getDilutedNetIncome() {
        return dilutedNetIncome;
    }

    public void setDilutedNetIncome(int dilutedNetIncome) {
        this.dilutedNetIncome = dilutedNetIncome;
    }

    public int getDilutedWeightedAverageShares() {
        return dilutedWeightedAverageShares;
    }

    public void setDilutedWeightedAverageShares(int dilutedWeightedAverageShares) {
        this.dilutedWeightedAverageShares = dilutedWeightedAverageShares;
    }

    public int getDilutedEpsExcludingExtraOrdItems() {
        return dilutedEpsExcludingExtraOrdItems;
    }

    public void setDilutedEpsExcludingExtraOrdItems(int dilutedEpsExcludingExtraOrdItems) {
        this.dilutedEpsExcludingExtraOrdItems = dilutedEpsExcludingExtraOrdItems;
    }

    public int getDpsCommonStockPrimaryIssue() {
        return dpsCommonStockPrimaryIssue;
    }

    public void setDpsCommonStockPrimaryIssue(int dpsCommonStockPrimaryIssue) {
        this.dpsCommonStockPrimaryIssue = dpsCommonStockPrimaryIssue;
    }

    public int getDilutedNormalizedEps() {
        return dilutedNormalizedEps;
    }

    public void setDilutedNormalizedEps(int dilutedNormalizedEps) {
        this.dilutedNormalizedEps = dilutedNormalizedEps;
    }
    
    
}
