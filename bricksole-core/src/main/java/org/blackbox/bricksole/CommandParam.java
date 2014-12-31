package org.blackbox.bricksole;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandParam {

    String value() default "";

    boolean required() default true;

    String defaultValue() default B.COMMAND_PARAMETER_DEFAULT_VALUE;

}
