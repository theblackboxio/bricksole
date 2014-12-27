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
     * Checks if the command exists in the context.
     *
     * @param command The command to check if exists in the context.
     * @return True if the command exists in the context false otherwise.
     */
    boolean containsCommend(Command command);

    /**
     * Adds a new pair command name - command if the command name has not been assigned.
     * If the command name has been already assigned then the action is ignored.
     *
     * @param commandName Command name.
     * @param command Command to be referenced by command name.
     */
    void addCommand(String commandName, Command command);

    /**
     * Adds a new map of pairs command name - command. All the command names already assigned
     * shall be ignored and not added.
     *
     * @param commands Map if command name - command to be added.
     */
    void addAllCommands(Map<String, ? extends Command> commands);

    /**
     * Adds a new pair command name - command.
     * If the command name has been already assigned then the old command is override by this.
     *
     * @param commandName Command name.
     * @param command Command to be referenced by command name.
     */
    void overrideCommand(String commandName, Command command);

    /**
     * Adds a new map of pairs command name - command. All the command names already assigned
     * shall be overriden.
     *
     * @param commands Map if command name - command to be added.
     */
    void overrideAllCommands(Map<String, ? extends Command> commands);

    /**
     * Removes the attachment between the command name and the attached command.
     *
     * @param commandName The command name to detach.
     */
    void remove(String commandName);

    /**
     * Returns the amount of command names in the context.
     *
     * @return The amount of command names in the context.
     */
    int size();

    /**
     * Returns if the context has or not commands.
     *
     * @return Returns True if the context has not commands stored, false otherwise.
     */
    boolean isEmpty();

}
