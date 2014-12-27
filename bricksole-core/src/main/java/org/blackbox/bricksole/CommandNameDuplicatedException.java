package org.blackbox.bricksole;

/**
 * Exception thrown in case of that the context is asked to hold two commands for the same name
 */
public class CommandNameDuplicatedException extends CommandContextException {

    public CommandNameDuplicatedException() {
    }

    public CommandNameDuplicatedException(String message) {
        super(message);
    }

    public CommandNameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandNameDuplicatedException(Throwable cause) {
        super(cause);
    }

    public CommandNameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
