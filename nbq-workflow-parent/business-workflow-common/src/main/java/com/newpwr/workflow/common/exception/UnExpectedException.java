package com.newpwr.workflow.common.exception;

/**
 * @author niubq
 * @date 2020/7/3 13:54
 * @description
 */
public class UnExpectedException extends RuntimeException {
    /**
     * Instantiates a new Should never happen exception.
     */
    public UnExpectedException() {
        super();
    }

    /**
     * Instantiates a new Should never happen exception.
     *
     * @param message the message
     */
    public UnExpectedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Should never happen exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public UnExpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Should never happen exception.
     *
     * @param cause the cause
     */
    public UnExpectedException(Throwable cause) {
        super(cause);
    }
}
