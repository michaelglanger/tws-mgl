package com.mgl.fundamental;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michael G. Langer on 21/10/2015.
 */
public class GeneralHelper {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public Date stringToDate(String str) {
        Date result = null;
        DateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            result = format.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(CompanyHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
