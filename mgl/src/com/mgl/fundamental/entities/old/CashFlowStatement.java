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
public class CashFlowStatement extends Statement{

//    <mapItem coaItem="ONET" statementType="CAS" lineID="10" precision="1">Net Income/Starting Line</mapItem>
    private int netIncomeStartingLine;
        
//    <mapItem coaItem="SDED" statementType="CAS" lineID="40" precision="1">Depreciation/Depletion</mapItem>
    private int depreciationDepletion;
            
//    <mapItem coaItem="SAMT" statementType="CAS" lineID="80" precision="1">Amortization</mapItem>
    private int amortization;
    
//    <mapItem coaItem="OBDT" statementType="CAS" lineID="90" precision="1">Deferred Taxes</mapItem>
    private int deferredTaxes;
    
//    <mapItem coaItem="SNCI" statementType="CAS" lineID="170" precision="1">Non-Cash Items</mapItem>
    private int nonCashItems;
    
//    <mapItem coaItem="SOCF" statementType="CAS" lineID="470" precision="1">Changes in Working Capital</mapItem>
    private int changesInWorkingCapital;
    
//    <mapItem coaItem="OTLO" statementType="CAS" lineID="480" precision="1">Cash from Operating Activities</mapItem>
    private int cashFromOperatingActivities;
    
//    <mapItem coaItem="SCEX" statementType="CAS" lineID="520" precision="1">Capital Expenditures</mapItem>
    private int CapitalExpenditures;
    
//    <mapItem coaItem="SICF" statementType="CAS" lineID="670" precision="1">Other Investing Cash Flow Items, Total</mapItem>
    private int otherInvestingCashFlowItemsTotal;
    
//    <mapItem coaItem="ITLI" statementType="CAS" lineID="680" precision="1">Cash from Investing Activities</mapItem>
    private int cashFromInvestingActivities;
    
//    <mapItem coaItem="SFCF" statementType="CAS" lineID="730" precision="1">Financing Cash Flow Items</mapItem>
    private int financingCashFlowItems;
    
//    <mapItem coaItem="FCDP" statementType="CAS" lineID="760" precision="1">Total Cash Dividends Paid</mapItem>
    private int totalCashDividendsPaid;
    
//    <mapItem coaItem="FPSS" statementType="CAS" lineID="880" precision="1">Issuance (Retirement) of Stock, Net</mapItem>
    private int issuanceRetirementOfStockNet;
            
//    <mapItem coaItem="FPRD" statementType="CAS" lineID="970" precision="1">Issuance (Retirement) of Debt, Net</mapItem>
    private int issuanceRetirementOfDebtNet;
    
//    <mapItem coaItem="FTLF" statementType="CAS" lineID="980" precision="1">Cash from Financing Activities</mapItem>
    private int cashFromFinancingActivities;
    
//    <mapItem coaItem="SFEE" statementType="CAS" lineID="990" precision="1">Foreign Exchange Effects</mapItem>
    private int foreignExchangeEffects;
    
//    <mapItem coaItem="SNCC" statementType="CAS" lineID="1000" precision="1">Net Change in Cash</mapItem>
    private int netChangeInCash;
    
//    <mapItem coaItem="SCIP" statementType="CAS" lineID="1040" precision="1">Cash Interest Paid</mapItem>
    private int cashInterestPaid;
    
//    <mapItem coaItem="SCTP" statementType="CAS" lineID="1050" precision="1">Cash Taxes Paid</mapItem>
    private int cashTaxesPaid;
    
    
    public CashFlowStatement(Company aCompany) {
        super(aCompany);
    }

    public int getNetIncomeStartingLine() {
        return netIncomeStartingLine;
    }

    public void setNetIncomeStartingLine(int netIncomeStartingLine) {
        this.netIncomeStartingLine = netIncomeStartingLine;
    }

    public int getDepreciationDepletion() {
        return depreciationDepletion;
    }

    public void setDepreciationDepletion(int depreciationDepletion) {
        this.depreciationDepletion = depreciationDepletion;
    }

    public int getAmortization() {
        return amortization;
    }

    public void setAmortization(int amortization) {
        this.amortization = amortization;
    }

    public int getDeferredTaxes() {
        return deferredTaxes;
    }

    public void setDeferredTaxes(int deferredTaxes) {
        this.deferredTaxes = deferredTaxes;
    }

    public int getNonCashItems() {
        return nonCashItems;
    }

    public void setNonCashItems(int nonCashItems) {
        this.nonCashItems = nonCashItems;
    }

    public int getChangesInWorkingCapital() {
        return changesInWorkingCapital;
    }

    public void setChangesInWorkingCapital(int changesInWorkingCapital) {
        this.changesInWorkingCapital = changesInWorkingCapital;
    }

    public int getCashFromOperatingActivities() {
        return cashFromOperatingActivities;
    }

    public void setCashFromOperatingActivities(int cashFromOperatingActivities) {
        this.cashFromOperatingActivities = cashFromOperatingActivities;
    }

    public int getCapitalExpenditures() {
        return CapitalExpenditures;
    }

    public void setCapitalExpenditures(int CapitalExpenditures) {
        this.CapitalExpenditures = CapitalExpenditures;
    }

    public int getOtherInvestingCashFlowItemsTotal() {
        return otherInvestingCashFlowItemsTotal;
    }

    public void setOtherInvestingCashFlowItemsTotal(int otherInvestingCashFlowItemsTotal) {
        this.otherInvestingCashFlowItemsTotal = otherInvestingCashFlowItemsTotal;
    }

    public int getCashFromInvestingActivities() {
        return cashFromInvestingActivities;
    }

    public void setCashFromInvestingActivities(int cashFromInvestingActivities) {
        this.cashFromInvestingActivities = cashFromInvestingActivities;
    }

    public int getFinancingCashFlowItems() {
        return financingCashFlowItems;
    }

    public void setFinancingCashFlowItems(int financingCashFlowItems) {
        this.financingCashFlowItems = financingCashFlowItems;
    }

    public int getTotalCashDividendsPaid() {
        return totalCashDividendsPaid;
    }

    public void setTotalCashDividendsPaid(int totalCashDividendsPaid) {
        this.totalCashDividendsPaid = totalCashDividendsPaid;
    }

    public int getIssuanceRetirementOfStockNet() {
        return issuanceRetirementOfStockNet;
    }

    public void setIssuanceRetirementOfStockNet(int issuanceRetirementOfStockNet) {
        this.issuanceRetirementOfStockNet = issuanceRetirementOfStockNet;
    }

    public int getIssuanceRetirementOfDebtNet() {
        return issuanceRetirementOfDebtNet;
    }

    public void setIssuanceRetirementOfDebtNet(int issuanceRetirementOfDebtNet) {
        this.issuanceRetirementOfDebtNet = issuanceRetirementOfDebtNet;
    }

    public int getCashFromFinancingActivities() {
        return cashFromFinancingActivities;
    }

    public void setCashFromFinancingActivities(int cashFromFinancingActivities) {
        this.cashFromFinancingActivities = cashFromFinancingActivities;
    }

    public int getForeignExchangeEffects() {
        return foreignExchangeEffects;
    }

    public void setForeignExchangeEffects(int foreignExchangeEffects) {
        this.foreignExchangeEffects = foreignExchangeEffects;
    }

    public int getNetChangeInCash() {
        return netChangeInCash;
    }

    public void setNetChangeInCash(int netChangeInCash) {
        this.netChangeInCash = netChangeInCash;
    }

    public int getCashInterestPaid() {
        return cashInterestPaid;
    }

    public void setCashInterestPaid(int cashInterestPaid) {
        this.cashInterestPaid = cashInterestPaid;
    }

    public int getCashTaxesPaid() {
        return cashTaxesPaid;
    }

    public void setCashTaxesPaid(int cashTaxesPaid) {
        this.cashTaxesPaid = cashTaxesPaid;
    }
    
}
