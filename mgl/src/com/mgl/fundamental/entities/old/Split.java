package com.mgl.fundamental.entities.old;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Michael G. Langer on 16/09/2015.
 */
public class Split {

    private Date date;
    private BigDecimal split;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSplit() {
        return split;
    }

    public void setSplit(BigDecimal split) {
        this.split = split;
    }
}
