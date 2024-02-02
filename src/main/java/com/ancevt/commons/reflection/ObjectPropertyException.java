package com.ancevt.commons.reflection;

public class ObjectPropertyException extends RuntimeException {

    public ObjectPropertyException() {
    }

    public ObjectPropertyException(String message) {
        super(message);
    }

    public ObjectPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectPropertyException(Throwable cause) {
        super(cause);
    }

    public ObjectPropertyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
