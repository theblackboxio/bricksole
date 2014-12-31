package org.blackbox.bricksole;

import java.lang.annotation.*;

/**
 * Annotation to declare a method parameter as a command parameter.
 */
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParam {

    /**
     * Name of the parameter in the command line. This value with a "-" as prefix is used to match
     * the command line values.
     */
    String value() default "";

    /**
     * True if the parameter is required, false otherwise.
     * Set a default value turns implicitly this value as false.
     */
    boolean required() default true;

    /**
     * The default value of the parameter in case that the parameter is missing in the command line.
     * Set this value implicitly turns the required value to false.
     */
    String defaultValue() default B.COMMAND_PARAMETER_DEFAULT_VALUE;

}
