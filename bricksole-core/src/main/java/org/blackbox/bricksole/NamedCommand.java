package org.blackbox.bricksole;

/**
 * Interface for a named command, that is a command object that contains itself its command name.
 *
 * This interface is used by org.blackbox.bricksole.ExplorerCommandContext to get the name of the
 * explored commands.
 *
 * @author guillermoblascojimenez
 * @see org.blackbox.bricksole.ExplorerCommandContext
 */
public interface NamedCommand extends Command {

    /**
     * Name of the command object.
     *
     * @return Name of the command object.
     */
    String getName();

}
