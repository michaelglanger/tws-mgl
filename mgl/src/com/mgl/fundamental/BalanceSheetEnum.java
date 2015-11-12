package com.mgl.fundamental;

/**
 * Created by Michael G. Langer on 19/10/2015.
 */
public enum BalanceSheetEnum {

    ACSH("Cash"),
    ACAE("Cash &amp; Equivalents"),
    ASTI("Short Term Investments"),
    SCSI("Cash and Short Term Investments"),
    AACR("Accounts Receivable - Trade, Net"),
    ATRC("Total Receivables, Net"),
    AITL("Total Inventory"),
    APPY("Prepaid Expenses"),
    SOCA("Other Current Assets, Total"),
    ATCA("Total Current Assets"),
    APTC("Property/Plant/Equipment, Total - Gross"),
    ADEP("Accumulated Depreciation, Total"),
    APPN("Property/Plant/Equipment, Total - Net"),
    AGWI("Goodwill, Net"),
    AINT("Intangibles, Net"),
    SINV("Long Term Investments"),
    ALTR("Note Receivable - Long Term"),
    SOLA("Other Long Term Assets, Total"),
    ATOT("Total Assets"),
    LAPB("Accounts Payable"),
    LPBA("Payable/Accrued"),
    LAEX("Accrued Expenses"),
    LSTD("Notes Payable/Short Term Debt"),
    LCLD("Current Port. of  LT Debt/Capital Leases"),
    SOCL("Other Current liabilities, Total"),
    LTCL("Total Current Liabilities"),
    LLTD("Long Term Debt"),
    LCLO("Capital Lease Obligations"),
    LTTD("Total Long Term Debt"),
    STLD("Total Debt"),
    SBDT("Deferred Income Tax"),
    LMIN("Minority Interest"),
    SLTL("Other Liabilities, Total"),
    LTLL("Total Liabilities"),
    SRPR("Redeemable Preferred Stock, Total"),
    SPRS("Preferred Stock - Non Redeemable, Net"),
    SCMS("Common Stock, Total"),
    QPIC("Additional Paid-In Capital"),
    QRED("Retained Earnings (Accumulated Deficit)"),
    QTSC("Treasury Stock - Common"),
    QEDG("ESOP Debt Guarantee"),
    QUGL("Unrealized Gain (Loss)"),
    SOTE("Other Equity, Total"),
    QTLE("Total Equity"),
    QTEL("Total Liabilities &amp; Shareholders' Equity"),
    QTCO("Total Common Shares Outstanding"),
    QTPO(">Total Preferred Shares Outstanding"),
    STBP("Tangible Book Value per Share, Common Eq");

    private final String name;

    private BalanceSheetEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    /* Returns the text */
    public String toString() {
        return this.name;
    }
}
