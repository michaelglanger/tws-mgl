package com.mgl.fundamental.entities.old;

/**
 * Created by Michael G. Langer on 16/09/2015.
 */
public class Issue {

    private int ID;
    private String type;
    private String description;
    private int order;

    private String name;
    private String ticker;
    private String cusip;
    private String isin;
    private String ric;
    private String sedol;
    private String displayRIC;
    private String instrumentPI;
    private String quotePI;

    private Exchange exchange;

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
}
