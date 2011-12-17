package com.jeremiahxu.learyperi.user;

import com.jeremiahxu.learyperi.LearyperiException;

/**
 * 用户管理抛出的异常
 * 
 * @author Jeremiah Xu
 * 
 */
public class UserException extends LearyperiException {

    private static final long serialVersionUID = 1L;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

}
