/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgl.fundamental.exceptions;

/**
 *
 * @author Michael Langer
 */
public class FundamentalParsingException extends Exception {

    public FundamentalParsingException() {
    }

    public FundamentalParsingException(String message) {
        super(message);
    }

    public FundamentalParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FundamentalParsingException(Throwable cause) {
        super(cause);
    }

    public FundamentalParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

 
    
}
