package com.jeremiahxu.learyperi.user;

import com.jeremiahxu.learyperi.LearyperiException;

/**
 * @author Jeremiah Xu
 * 
 */
public class ResourceException extends LearyperiException {

    private static final long serialVersionUID = 1L;

    public ResourceException() {
        super();
    }

    /**
     * @param message
     */
    public ResourceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ResourceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

}
