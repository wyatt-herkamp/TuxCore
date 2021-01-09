package me.kingtux.tuxcore;

public class TuxCoreException extends Exception{
    public TuxCoreException() {
    }

    public TuxCoreException(String message) {
        super(message);
    }

    public TuxCoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public TuxCoreException(Throwable cause) {
        super(cause);
    }

    public TuxCoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
