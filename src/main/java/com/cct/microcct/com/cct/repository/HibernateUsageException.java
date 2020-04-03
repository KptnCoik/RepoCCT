package com.cct.microcct.com.cct.repository;

public class HibernateUsageException extends Exception {

    private static final long serialVersionUID = 1L;

    public HibernateUsageException() {
        super();
    }

    public HibernateUsageException(String message, Throwable cause) {
        super(message, cause);
    }

    public HibernateUsageException(String message) {
        super(message);
    }

    public HibernateUsageException(Throwable cause) {
        super(cause);
    }

}
