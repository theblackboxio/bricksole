package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
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
    protected final ConcurrentMap<String, CallableCommand> commands;
    private Status status;

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
        this.status = Status.DECLARED;
    }

    @Override
    public void execute(String commandName, List<String> args) throws CommandNotFoundException, CommandCallException {
        if (!isConfigured()) {
            throw new CommandRuntimeException("Command context has been asked to execute a command but context is not configured.");
        }
        CallableCommand command = commands.get(commandName);
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
    public boolean isConfigured() {
        return Status.CONFIGURED.equals(this.status);
    }

    @Override
    public void configure() throws CommandInitializationException {
        this.status = Status.CONFIGURED;
    }
}
