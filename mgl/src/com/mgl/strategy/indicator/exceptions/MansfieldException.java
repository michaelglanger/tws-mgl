package com.mgl.strategy.indicator.exceptions;

/**
 * Created by Michael G. Langer on 5/27/2015.
 */
public class MansfieldException extends Exception {

    public MansfieldException() {
    }

    public MansfieldException(String message) {
        super(message);
    }

    public MansfieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public MansfieldException(Throwable cause) {
        super(cause);
    }

    public MansfieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
