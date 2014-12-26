package org.blackbox.bricksole;

import java.util.List;

/**
 * A command context is an object that contains named references to Command objects and the
 * mechanisms to write and read.
 * When an execution is required the command name and arguments is passed and the command context
 * finds the referenced command by the passed name and executes it with the given arguments.
 */
public interface CommandContext {

    /**
     * Executes the command referenced by the command name.
     *
     * @param commandName Command name reference.
     * @param arguments Arguments to be passed to the command.
     */
    void execute(String commandName, List<String> arguments);
}
