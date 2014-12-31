package org.blackbox.bricksole;

/**
 * Exception thrown in the call phase.
 */
public class CommandCallException extends CommandRuntimeException {

    public CommandCallException() {
    }

    public CommandCallException(String message) {
        super(message);
    }

    public CommandCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandCallException(Throwable cause) {
        super(cause);
    }

    public CommandCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
