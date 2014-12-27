package org.blackbox.bricksole;

/**
 * Exception of command context environment.
 */
public class CommandContextException extends Exception {

    public CommandContextException() {
    }

    public CommandContextException(String message) {
        super(message);
    }

    public CommandContextException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandContextException(Throwable cause) {
        super(cause);
    }

    public CommandContextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
