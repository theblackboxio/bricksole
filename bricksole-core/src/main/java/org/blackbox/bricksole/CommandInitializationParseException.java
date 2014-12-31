package org.blackbox.bricksole;

/**
 * Created by guillermoblascojimenez on 30/12/14.
 */
public class CommandInitializationParseException extends CommandInitializationException {

    public CommandInitializationParseException() {
    }

    public CommandInitializationParseException(String message) {
        super(message);
    }

    public CommandInitializationParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandInitializationParseException(Throwable cause) {
        super(cause);
    }

    public CommandInitializationParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
