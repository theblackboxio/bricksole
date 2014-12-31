package org.blackbox.bricksole;

/**
 * Created by guillermoblascojimenez on 31/12/14.
 */
public class RequiredParameterNotFoundException extends CommandRuntimeException {
    public RequiredParameterNotFoundException() {
    }

    public RequiredParameterNotFoundException(String message) {
        super(message);
    }

    public RequiredParameterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequiredParameterNotFoundException(Throwable cause) {
        super(cause);
    }

    public RequiredParameterNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
