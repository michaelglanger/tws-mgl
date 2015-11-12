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
public class FundamentalMissingExchangeException extends RuntimeException {

    public FundamentalMissingExchangeException() {
    }

    public FundamentalMissingExchangeException(String message) {
        super(message);
    }

    public FundamentalMissingExchangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FundamentalMissingExchangeException(Throwable cause) {
        super(cause);
    }

    public FundamentalMissingExchangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
