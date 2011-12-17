package com.jeremiahxu.learyperi.menu;

import com.jeremiahxu.learyperi.LearyperiException;

/**
 * 菜单相关异常
 * 
 * @author Jeremiah Xu
 * 
 */
public class MenuException extends LearyperiException {

    private static final long serialVersionUID = 1L;

    public MenuException() {
        super();
    }

    /**
     * @param message
     */
    public MenuException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public MenuException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public MenuException(String message, Throwable cause) {
        super(message, cause);
    }

}
