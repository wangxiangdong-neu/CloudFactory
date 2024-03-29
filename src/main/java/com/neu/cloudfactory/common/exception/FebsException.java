package com.neu.cloudfactory.common.exception;

/**
 * FEBS系统内部异常
 *
 * @author wxd
 */
public class FebsException extends RuntimeException {

    private static final long serialVersionUID = -994962710559017255L;

    public FebsException(String message) {
        super(message);
    }
}
