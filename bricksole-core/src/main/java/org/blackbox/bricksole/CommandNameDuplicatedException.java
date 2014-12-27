package org.blackbox.bricksole;

/**
 * Created by guillermoblascojimenez on 27/12/14.
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
