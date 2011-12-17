package com.jeremiahxu.learyperi;

/**
 * @author Jeremiah Xu
 * 
 */
public class LearyperiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LearyperiException() {
        super();
    }

    /**
     * @param message
     */
    public LearyperiException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public LearyperiException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public LearyperiException(String message, Throwable cause) {
        super(message, cause);
    }

}
