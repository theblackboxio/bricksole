package org.blackbox.bricksole;

/**
 * Created by guillermoblascojimenez on 30/12/14.
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
