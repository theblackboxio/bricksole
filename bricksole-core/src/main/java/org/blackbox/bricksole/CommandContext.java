package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;

/**
 * A command context is an object that contains named references to Command objects and the
 * mechanisms to write and read.
 * When an execution is required the command name and arguments is passed and the command context
 * finds the referenced command by the passed name and executes it with the given arguments.
 */
public interface CommandContext {

    /**
     * Possible status of a CommandContext.
     */
    enum Status {
        /** The context has been declared but not configured */
        DECLARED,
        /** The context has been declared and configured */
        CONFIGURED
    }

    /**
     * Executes the command referenced by the command name.
     *
     * @param commandName Command name reference.
     * @param arguments Arguments to be passed to the command.
     */
    void execute(String commandName, List<String> arguments) throws CommandNotFoundException, CommandCallException;

    /**
     * Returns the print stream of the command context.
     *
     * @return The print stream of the command context.
     */
    PrintStream getPrintStream();

    /**
     * Returns the input stream of the command context.
     *
     * @return The input stream of the command context.
     */
    InputStream getInputStream();

    /**
     * The command names stored in the command context.
     *
     * @return The set of command names stored in this.
     */
    Set<String> commandNames();

    /**
     * Checks if there is a command attached to this command name.
     *
     * @param commandName The command name to ask for.
     * @return True if there is a command attached to the command name, false otherwise.
     */
    boolean containsCommandName(String commandName);

    /**
     * Returns if the context is configured or not.
     * @return True if the context is configured, false otherwise.
     */
    boolean isConfigured();

    /**
     * Configures the context.
     */
    void configure() throws CommandInitializationException;
}
