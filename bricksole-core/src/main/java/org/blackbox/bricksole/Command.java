package org.blackbox.bricksole;

import java.lang.annotation.*;

/**
 * Annotation to declare a method as Command. Declaring a Class as Command adds the given value
 * as a prefix of the command name.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    /**
     * Name of the command in case of methods, prefix of the command in case of types.
     */
    String value() default "";
}
