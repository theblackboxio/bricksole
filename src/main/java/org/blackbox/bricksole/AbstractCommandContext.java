package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by guillermoblascojimenez on 26/12/14.
 */
abstract class AbstractCommandContext<C extends Command> implements CommandContext {
    protected final PrintStream printStream;
    protected final InputStream inputStream;
    protected final ConcurrentMap<String, C> commands;

    public AbstractCommandContext() {
        this(System.out, System.in);
    }

    public AbstractCommandContext(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
        commands = new ConcurrentHashMap<>();
    }

    @Override
    public void execute(String commandName, List<String> args){
        commands.get(commandName).execute(printStream, inputStream, args);
    }
}
