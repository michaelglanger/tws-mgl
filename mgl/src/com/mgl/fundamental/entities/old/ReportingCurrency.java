package com.mgl.fundamental.entities.old;

import java.math.BigDecimal;

/**
 * Created by Michael G. Langer on 16/09/2015.
 */
public enum ReportingCurrency {
    USD ("U.S. Dollars");

    private String name;

    private ReportingCurrency(String a) {
        name = a;
    }
}
