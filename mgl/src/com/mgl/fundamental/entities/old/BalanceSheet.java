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
public class BalanceSheet extends Statement {

//    <mapItem coaItem="ACSH" statementType="BAL" lineID="10" precision="1">Cash</mapItem>
    private int cash;
    
//    <mapItem coaItem="ACAE" statementType="BAL" lineID="20" precision="1">Cash &amp; Equivalents</mapItem>
    private int cashEquivalents;
    
//    <mapItem coaItem="ASTI" statementType="BAL" lineID="30" precision="1">Short Term Investments</mapItem>
    private int shortTermInvestments;
    
//    <mapItem coaItem="SCSI" statementType="BAL" lineID="40" precision="1">Cash and Short Term Investments</mapItem>
    private int cashAndShortTermInvestments;
    
//    <mapItem coaItem="AACR" statementType="BAL" lineID="70" precision="1">Accounts Receivable - Trade, Net</mapItem>
    private int accountsReceivableTradeNet;
    
//    <mapItem coaItem="ATRC" statementType="BAL" lineID="100" precision="1">Total Receivables, Net</mapItem>
    private int totalReceivablesNet;
    
//    <mapItem coaItem="AITL" statementType="BAL" lineID="180" precision="1">Total Inventory</mapItem>
    private int totalInventory;
    
//    <mapItem coaItem="APPY" statementType="BAL" lineID="190" precision="1">Prepaid Expenses</mapItem>
    private int prepaidExpenses;
    
//    <mapItem coaItem="SOCA" statementType="BAL" lineID="260" precision="1">Other Current Assets, Total</mapItem>
    private int otherCurrentAssetsTotal;
    
//    <mapItem coaItem="ATCA" statementType="BAL" lineID="270" precision="1">Total Current Assets</mapItem>
    private int totalCurrentAssets;
    
//    <mapItem coaItem="APTC" statementType="BAL" lineID="520" precision="1">Property/Plant/Equipment, Total - Gross</mapItem>
    private int propertyPlantEquiptmentTotalGross;
    
//    <mapItem coaItem="ADEP" statementType="BAL" lineID="530" precision="1">Accumulated Depreciation, Total</mapItem>
    private int accumulatedDepreciation;
    
//    <mapItem coaItem="APPN" statementType="BAL" lineID="540" precision="1">Property/Plant/Equipment, Total - Net</mapItem>
    private int propertyPlantEquipmentTotal;

//    <mapItem coaItem="AGWI" statementType="BAL" lineID="570" precision="1">Goodwill, Net</mapItem>
    private int goodwillNet;

//    <mapItem coaItem="AINT" statementType="BAL" lineID="600" precision="1">Intangibles, Net</mapItem>
    private int intangiblesNet;

//    <mapItem coaItem="SINV" statementType="BAL" lineID="690" precision="1">Long Term Investments</mapItem>
    private int longTermInvestments;

//    <mapItem coaItem="ALTR" statementType="BAL" lineID="710" precision="1">Note Receivable - Long Term</mapItem>
    private int noteReceivableLongTerm;

//    <mapItem coaItem="SOLA" statementType="BAL" lineID="780" precision="1">Other Long Term Assets, Total</mapItem>
    private int otherLongTermAssetsTotal;

//    <mapItem coaItem="ATOT" statementType="BAL" lineID="880" precision="1">Total Assets</mapItem>
    private int totalAssets;

//    <mapItem coaItem="LAPB" statementType="BAL" lineID="890" precision="1">Accounts Payable</mapItem>
    private int accountsPayable;

//    <mapItem coaItem="LPBA" statementType="BAL" lineID="900" precision="1">Payable/Accrued</mapItem>
    private int payableAccrued;

//    <mapItem coaItem="LAEX" statementType="BAL" lineID="910" precision="1">Accrued Expenses</mapItem>
    private int accruedExpenses;

//    <mapItem coaItem="LSTD" statementType="BAL" lineID="1120" precision="1">Notes Payable/Short Term Debt</mapItem>
    private int notesPayableShortTermDebt;

//    <mapItem coaItem="LCLD" statementType="BAL" lineID="1130" precision="1">Current Port. of  LT Debt/Capital Leases</mapItem>
    private int currentPortOfLTDebtCapitalLeases;

//    <mapItem coaItem="SOCL" statementType="BAL" lineID="1220" precision="1">Other Current liabilities, Total</mapItem>
    private int otherCurrentLiabilitiesTotal;

//    <mapItem coaItem="LTCL" statementType="BAL" lineID="1230" precision="1">Total Current Liabilities</mapItem>
    private int totalCurrentLiabilities;

//    <mapItem coaItem="LLTD" statementType="BAL" lineID="1240" precision="1">Long Term Debt</mapItem>
    private int longTermDebt;

//    <mapItem coaItem="LCLO" statementType="BAL" lineID="1250" precision="1">Capital Lease Obligations</mapItem>
    private int capitalLeaseObligations;

//    <mapItem coaItem="LTTD" statementType="BAL" lineID="1260" precision="1">Total Long Term Debt</mapItem>
    private int totalLongTermDebt;

//    <mapItem coaItem="STLD" statementType="BAL" lineID="1270" precision="1">Total Debt</mapItem>
    private int totalDebt;

//    <mapItem coaItem="SBDT" statementType="BAL" lineID="1300" precision="1">Deferred Income Tax</mapItem>
    private int defferedIncomeTax;

//    <mapItem coaItem="LMIN" statementType="BAL" lineID="1310" precision="1">Minority Interest</mapItem>
    private int minorityInterest;

//    <mapItem coaItem="SLTL" statementType="BAL" lineID="1370" precision="1">Other Liabilities, Total</mapItem>
    private int otherLiabilitiesTotal;

//    <mapItem coaItem="LTLL" statementType="BAL" lineID="1380" precision="1">Total Liabilities</mapItem>
    private int totalLiabilities;

//    <mapItem coaItem="SRPR" statementType="BAL" lineID="1410" precision="1">Redeemable Preferred Stock, Total</mapItem>
    private int redeemablePreferedStockTotal;

//    <mapItem coaItem="SPRS" statementType="BAL" lineID="1460" precision="1">Preferred Stock - Non Redeemable, Net</mapItem>
    private int preferredStockNonRedeemableNet;

//    <mapItem coaItem="SCMS" statementType="BAL" lineID="1490" precision="1">Common Stock, Total</mapItem>
    private int commonStockTotal;

//    <mapItem coaItem="QPIC" statementType="BAL" lineID="1500" precision="1">Additional Paid-In Capital</mapItem>
    private int additionalPaidInCapital;

//    <mapItem coaItem="QRED" statementType="BAL" lineID="1510" precision="1">Retained Earnings (Accumulated Deficit)</mapItem>
    private int retainedEarningsAccumulatedDeficit;

//    <mapItem coaItem="QTSC" statementType="BAL" lineID="1520" precision="1">Treasury Stock - Common</mapItem>
    private int treasuryStockCommon;

//    <mapItem coaItem="QEDG" statementType="BAL" lineID="1530" precision="1">ESOP Debt Guarantee</mapItem>
    private int esopDebtGuarantee;

//    <mapItem coaItem="QUGL" statementType="BAL" lineID="1540" precision="1">Unrealized Gain (Loss)</mapItem>
    private int unrealizedGainLoss;

//    <mapItem coaItem="SOTE" statementType="BAL" lineID="1590" precision="1">Other Equity, Total</mapItem>
    private int otherEquityTotal;

//    <mapItem coaItem="QTLE" statementType="BAL" lineID="1600" precision="1">Total Equity</mapItem>
    private int totalEquity;

//    <mapItem coaItem="QTEL" statementType="BAL" lineID="1610" precision="1">Total Liabilities &amp; Shareholders' Equity</mapItem>
    private int totalLiabilitiesAndShareholdersEquity;

//    <mapItem coaItem="QTCO" statementType="BAL" lineID="1660" precision="2">Total Common Shares Outstanding</mapItem>
    private int totalCommonSharesOutstanding;

//    <mapItem coaItem="QTPO" statementType="BAL" lineID="1770" precision="2">Total Preferred Shares Outstanding</mapItem>
    private int totalPreferredSharesOutstanding;

//    <mapItem coaItem="STBP" statementType="BAL" lineID="1980" precision="3">Tangible Book Value per Share, Common Eq</mapItem>
    private int tangibleBookValuePerShareCommonEq;

    public BalanceSheet(Company aCompany) {
        super(aCompany);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCashEquivalents() {
        return cashEquivalents;
    }

    public void setCashEquivalents(int cashEquivalents) {
        this.cashEquivalents = cashEquivalents;
    }

    public int getShortTermInvestments() {
        return shortTermInvestments;
    }

    public void setShortTermInvestments(int shortTermInvestments) {
        this.shortTermInvestments = shortTermInvestments;
    }

    public int getCashAndShortTermInvestments() {
        return cashAndShortTermInvestments;
    }

    public void setCashAndShortTermInvestments(int cashAndShortTermInvestments) {
        this.cashAndShortTermInvestments = cashAndShortTermInvestments;
    }

    public int getAccountsReceivableTradeNet() {
        return accountsReceivableTradeNet;
    }

    public void setAccountsReceivableTradeNet(int accountsReceivableTradeNet) {
        this.accountsReceivableTradeNet = accountsReceivableTradeNet;
    }

    public int getTotalReceivablesNet() {
        return totalReceivablesNet;
    }

    public void setTotalReceivablesNet(int totalReceivablesNet) {
        this.totalReceivablesNet = totalReceivablesNet;
    }

    public int getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(int totalInventory) {
        this.totalInventory = totalInventory;
    }

    public int getPrepaidExpenses() {
        return prepaidExpenses;
    }

    public void setPrepaidExpenses(int prepaidExpenses) {
        this.prepaidExpenses = prepaidExpenses;
    }

    public int getOtherCurrentAssetsTotal() {
        return otherCurrentAssetsTotal;
    }

    public void setOtherCurrentAssetsTotal(int otherCurrentAssetsTotal) {
        this.otherCurrentAssetsTotal = otherCurrentAssetsTotal;
    }

    public int getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    public void setTotalCurrentAssets(int totalCurrentAssets) {
        this.totalCurrentAssets = totalCurrentAssets;
    }

    public int getPropertyPlantEquiptmentTotalGross() {
        return propertyPlantEquiptmentTotalGross;
    }

    public void setPropertyPlantEquiptmentTotalGross(int propertyPlantEquiptmentTotalGross) {
        this.propertyPlantEquiptmentTotalGross = propertyPlantEquiptmentTotalGross;
    }

    public int getAccumulatedDepreciation() {
        return accumulatedDepreciation;
    }

    public void setAccumulatedDepreciation(int accumulatedDepreciation) {
        this.accumulatedDepreciation = accumulatedDepreciation;
    }

    public int getPropertyPlantEquipmentTotal() {
        return propertyPlantEquipmentTotal;
    }

    public void setPropertyPlantEquipmentTotal(int propertyPlantEquipmentTotal) {
        this.propertyPlantEquipmentTotal = propertyPlantEquipmentTotal;
    }

    public int getGoodwillNet() {
        return goodwillNet;
    }

    public void setGoodwillNet(int goodwillNet) {
        this.goodwillNet = goodwillNet;
    }

    public int getIntangiblesNet() {
        return intangiblesNet;
    }

    public void setIntangiblesNet(int intangiblesNet) {
        this.intangiblesNet = intangiblesNet;
    }

    public int getLongTermInvestments() {
        return longTermInvestments;
    }

    public void setLongTermInvestments(int longTermInvestments) {
        this.longTermInvestments = longTermInvestments;
    }

    public int getNoteReceivableLongTerm() {
        return noteReceivableLongTerm;
    }

    public void setNoteReceivableLongTerm(int noteReceivableLongTerm) {
        this.noteReceivableLongTerm = noteReceivableLongTerm;
    }

    public int getOtherLongTermAssetsTotal() {
        return otherLongTermAssetsTotal;
    }

    public void setOtherLongTermAssetsTotal(int otherLongTermAssetsTotal) {
        this.otherLongTermAssetsTotal = otherLongTermAssetsTotal;
    }

    public int getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(int totalAssets) {
        this.totalAssets = totalAssets;
    }

    public int getAccountsPayable() {
        return accountsPayable;
    }

    public void setAccountsPayable(int accountsPayable) {
        this.accountsPayable = accountsPayable;
    }

    public int getPayableAccrued() {
        return payableAccrued;
    }

    public void setPayableAccrued(int payableAccrued) {
        this.payableAccrued = payableAccrued;
    }

    public int getAccruedExpenses() {
        return accruedExpenses;
    }

    public void setAccruedExpenses(int accruedExpenses) {
        this.accruedExpenses = accruedExpenses;
    }

    public int getNotesPayableShortTermDebt() {
        return notesPayableShortTermDebt;
    }

    public void setNotesPayableShortTermDebt(int notesPayableShortTermDebt) {
        this.notesPayableShortTermDebt = notesPayableShortTermDebt;
    }

    public int getCurrentPortOfLTDebtCapitalLeases() {
        return currentPortOfLTDebtCapitalLeases;
    }

    public void setCurrentPortOfLTDebtCapitalLeases(int currentPortOfLTDebtCapitalLeases) {
        this.currentPortOfLTDebtCapitalLeases = currentPortOfLTDebtCapitalLeases;
    }

    public int getOtherCurrentLiabilitiesTotal() {
        return otherCurrentLiabilitiesTotal;
    }

    public void setOtherCurrentLiabilitiesTotal(int otherCurrentLiabilitiesTotal) {
        this.otherCurrentLiabilitiesTotal = otherCurrentLiabilitiesTotal;
    }

    public int getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    public void setTotalCurrentLiabilities(int totalCurrentLiabilities) {
        this.totalCurrentLiabilities = totalCurrentLiabilities;
    }

    public int getLongTermDebt() {
        return longTermDebt;
    }

    public void setLongTermDebt(int longTermDebt) {
        this.longTermDebt = longTermDebt;
    }

    public int getCapitalLeaseObligations() {
        return capitalLeaseObligations;
    }

    public void setCapitalLeaseObligations(int capitalLeaseObligations) {
        this.capitalLeaseObligations = capitalLeaseObligations;
    }

    public int getTotalLongTermDebt() {
        return totalLongTermDebt;
    }

    public void setTotalLongTermDebt(int totalLongTermDebt) {
        this.totalLongTermDebt = totalLongTermDebt;
    }

    public int getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(int totalDebt) {
        this.totalDebt = totalDebt;
    }

    public int getDefferedIncomeTax() {
        return defferedIncomeTax;
    }

    public void setDefferedIncomeTax(int defferedIncomeTax) {
        this.defferedIncomeTax = defferedIncomeTax;
    }

    public int getMinorityInterest() {
        return minorityInterest;
    }

    public void setMinorityInterest(int minorityInterest) {
        this.minorityInterest = minorityInterest;
    }

    public int getOtherLiabilitiesTotal() {
        return otherLiabilitiesTotal;
    }

    public void setOtherLiabilitiesTotal(int otherLiabilitiesTotal) {
        this.otherLiabilitiesTotal = otherLiabilitiesTotal;
    }

    public int getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(int totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public int getRedeemablePreferedStockTotal() {
        return redeemablePreferedStockTotal;
    }

    public void setRedeemablePreferedStockTotal(int redeemablePreferedStockTotal) {
        this.redeemablePreferedStockTotal = redeemablePreferedStockTotal;
    }

    public int getPreferredStockNonRedeemableNet() {
        return preferredStockNonRedeemableNet;
    }

    public void setPreferredStockNonRedeemableNet(int preferredStockNonRedeemableNet) {
        this.preferredStockNonRedeemableNet = preferredStockNonRedeemableNet;
    }

    public int getCommonStockTotal() {
        return commonStockTotal;
    }

    public void setCommonStockTotal(int commonStockTotal) {
        this.commonStockTotal = commonStockTotal;
    }

    public int getAdditionalPaidInCapital() {
        return additionalPaidInCapital;
    }

    public void setAdditionalPaidInCapital(int additionalPaidInCapital) {
        this.additionalPaidInCapital = additionalPaidInCapital;
    }

    public int getRetainedEarningsAccumulatedDeficit() {
        return retainedEarningsAccumulatedDeficit;
    }

    public void setRetainedEarningsAccumulatedDeficit(int retainedEarningsAccumulatedDeficit) {
        this.retainedEarningsAccumulatedDeficit = retainedEarningsAccumulatedDeficit;
    }

    public int getTreasuryStockCommon() {
        return treasuryStockCommon;
    }

    public void setTreasuryStockCommon(int treasuryStockCommon) {
        this.treasuryStockCommon = treasuryStockCommon;
    }

    public int getEsopDebtGuarantee() {
        return esopDebtGuarantee;
    }

    public void setEsopDebtGuarantee(int esopDebtGuarantee) {
        this.esopDebtGuarantee = esopDebtGuarantee;
    }

    public int getUnrealizedGainLoss() {
        return unrealizedGainLoss;
    }

    public void setUnrealizedGainLoss(int unrealizedGainLoss) {
        this.unrealizedGainLoss = unrealizedGainLoss;
    }

    public int getOtherEquityTotal() {
        return otherEquityTotal;
    }

    public void setOtherEquityTotal(int otherEquityTotal) {
        this.otherEquityTotal = otherEquityTotal;
    }

    public int getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(int totalEquity) {
        this.totalEquity = totalEquity;
    }

    public int getTotalLiabilitiesAndShareholdersEquity() {
        return totalLiabilitiesAndShareholdersEquity;
    }

    public void setTotalLiabilitiesAndShareholdersEquity(int totalLiabilitiesAndShareholdersEquity) {
        this.totalLiabilitiesAndShareholdersEquity = totalLiabilitiesAndShareholdersEquity;
    }

    public int getTotalCommonSharesOutstanding() {
        return totalCommonSharesOutstanding;
    }

    public void setTotalCommonSharesOutstanding(int totalCommonSharesOutstanding) {
        this.totalCommonSharesOutstanding = totalCommonSharesOutstanding;
    }

    public int getTotalPreferredSharesOutstanding() {
        return totalPreferredSharesOutstanding;
    }

    public void setTotalPreferredSharesOutstanding(int totalPreferredSharesOutstanding) {
        this.totalPreferredSharesOutstanding = totalPreferredSharesOutstanding;
    }

    public int getTangibleBookValuePerShareCommonEq() {
        return tangibleBookValuePerShareCommonEq;
    }

    public void setTangibleBookValuePerShareCommonEq(int tangibleBookValuePerShareCommonEq) {
        this.tangibleBookValuePerShareCommonEq = tangibleBookValuePerShareCommonEq;
    }
}
