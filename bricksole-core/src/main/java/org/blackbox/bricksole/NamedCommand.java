package org.blackbox.bricksole;

import java.lang.annotation.*;

/**
 * Annotation for a named command, that is a command object that contains itself its command name.
 *
 * This annotation is used by org.blackbox.bricksole.ExplorerCommandContext to get the name of the
 * explored commands.
 *
 * @author guillermoblascojimenez
 * @see org.blackbox.bricksole.ExplorerCommandContext
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NamedCommand {

    /**
     * Name of the command object.
     *
     * @return Name of the command object.
     */
    String value();

}
