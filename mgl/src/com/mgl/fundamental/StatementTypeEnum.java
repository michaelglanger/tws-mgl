package com.mgl.fundamental;

/**
 * Created by Michael G. Langer on 22/10/2015.
 */
public enum StatementTypeEnum {

    INC("Income statement"),
    BAL("Balance sheet"),
    CAS("Cashflow statement");

    private final String name;

    private StatementTypeEnum(String s) {
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
