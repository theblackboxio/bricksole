package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by guillermoblascojimenez on 26/12/14.
 */
abstract class AbstractCommandContext implements CommandContext {

    protected final PrintStream printStream;
    protected final InputStream inputStream;
    protected final ConcurrentMap<String, Command> commands;

    public AbstractCommandContext() {
        this(System.out, System.in);
    }

    public AbstractCommandContext(PrintStream printStream, InputStream inputStream) {
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
