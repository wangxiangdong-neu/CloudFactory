package com.neu.cloudfactory.common.exception;

/**
 * 限流异常
 *
 * @author wxd
 */
public class LimitAccessException extends FebsException {

    private static final long serialVersionUID = -3608667856397125671L;

    public LimitAccessException(String message) {
        super(message);
    }
}