package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * An abstract implementation of CommandContext. Holds the streams and a concurrent map of commands.
 * Also implements all map-like functions, some getters and a simple implementation of
 * execute method.
 */
abstract class AbstractCommandContext implements CommandContext {

    private final PrintStream printStream;
    private final InputStream inputStream;
    protected final ConcurrentMap<String, Command> commands;

    /**
     * Builds the command context with System.out and System.in as default streams.
     */
    AbstractCommandContext() {
        this(System.out, System.in);
    }

    /**
     * Builds the context with the given parameters.
     *
     * @param printStream PrintStream where the commands outputs.
     * @param inputStream InputStream where the commands reads.
     */
    AbstractCommandContext(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
        this.commands = new ConcurrentHashMap<>();
    }

    @Override
    public void execute(String commandName, List<String> args) throws CommandNotFoundException {
        Command command = commands.get(commandName);
        if (command == null) {
            throw new CommandNotFoundException("Command with name \"" + commandName + "\" not found in context.");
        } else {
            command.execute(printStream, inputStream, args);
        }
    }

    @Override
    public PrintStream getPrintStream() {
        return printStream;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public Set<String> commandNames() {
        return commands.keySet();
    }

    @Override
    public boolean containsCommandName(String commandName) {
        return commands.containsKey(commandName);
    }

    @Override
    public boolean containsCommend(Command command) {
        return commands.containsValue(command);
    }

    @Override
    public void addCommand(String commandName, Command command) {
        commands.putIfAbsent(commandName, command);
    }

    @Override
    public void addAllCommands(Map<String, ? extends Command> commands) {
        for (Map.Entry<String, ? extends Command> command : commands.entrySet()) {
            this.commands.putIfAbsent(command.getKey(), command.getValue());
        }
    }

    @Override
    public void overrideCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    @Override
    public void overrideAllCommands(Map<String, ? extends Command> commands) {
        this.commands.putAll(commands);
    }

    @Override
    public void remove(String commandName) {
        commands.remove(commandName);
    }

    @Override
    public int size() {
        return commands.size();
    }

    @Override
    public boolean isEmpty() {
        return commands.isEmpty();
    }
}
