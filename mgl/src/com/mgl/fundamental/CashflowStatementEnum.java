package com.mgl.fundamental;

/**
 * Created by Michael G. Langer on 21/10/2015.
 */
public enum CashflowStatementEnum {

    ONET("Net Income/Starting Line"),
    SDED("Depreciation/Depletion"),
    SAMT("Amortization"),
    OBDT("Deferred Taxes"),
    SNCI("Non-Cash Items"),
    SOCF("Changes in Working Capital"),
    OTLO("Cash from Operating Activities"),
    SCEX("Capital Expenditures"),
    SICF("Other Investing Cash Flow Items, Total"),
    ITLI("Cash from Investing Activities"),
    SFCF("Financing Cash Flow Items"),
    FCDP("Total Cash Dividends Paid"),
    FPSS("Issuance (Retirement) of Stock, Net"),
    FPRD("Issuance (Retirement) of Debt, Net"),
    FTLF("Cash from Financing Activities"),
    SFEE("Foreign Exchange Effects"),
    SNCC("Net Change in Cash"),
    SCIP("Cash Interest Paid"),
    SCTP("Cash Taxes Paid");

    private final String name;

    private CashflowStatementEnum(String s) {
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
