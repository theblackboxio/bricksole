package org.blackbox.bricksole;

/**
 * Bricksole constants
 */
public final class B {

    private B() { }

    /**
     * Because annotation values do not allow null values, we have to define a null-like value.
     * When a default value of a command parameter is this value we assume that is null.
     *
     * See Spring RequestParam annotation's default value.
     */
    static final String COMMAND_PARAMETER_DEFAULT_VALUE = "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";
}
