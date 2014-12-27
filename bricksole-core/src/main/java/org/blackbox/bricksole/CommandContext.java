package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    void execute(String commandName, List<String> arguments) throws CommandNotFoundException;

    PrintStream getPrintStream();

    InputStream getInputStream();

    /**
     * Returns the command names stored in the command context.
     *
     * @return The set of command names stored in this.
     */
    Set<String> commandNames();

    boolean containsCommandName(String commandName);

    boolean containsCommend(Command command);

    void addCommand(String commandName, Command command);

    void addAllCommands(Map<String, ? extends Command> commands);

    void overrideCommand(String commandName, Command command);

    void overrideAllCommands(Map<String, ? extends Command> commands);

    void remove(String commandName);

    int size();

    boolean isEmpty();

}
