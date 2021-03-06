package org.blackbox.bricksole;

/**
 * Exception thrown during the command initialization phase.
 */
public class CommandInitializationException extends CommandContextException {

    public CommandInitializationException() {
    }

    public CommandInitializationException(String message) {
        super(message);
    }

    public CommandInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandInitializationException(Throwable cause) {
        super(cause);
    }

    public CommandInitializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
