package com.thalesgroup.hudson.plugins.cccc;

/**
 * @author Gregory Boissinot
 */
public class CCCCException extends Exception {

    public CCCCException() {
    }

    public CCCCException(String s) {
        super(s);
    }

    public CCCCException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CCCCException(Throwable throwable) {
        super(throwable);
    }
}
