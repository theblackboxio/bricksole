package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * This class wraps a command into a NamedCommand given a name.
 *
 * @author guillermoblascojimenez
 * @see org.blackbox.bricksole.NamedCommand
 */
public class NamedCommandWraper implements NamedCommand {

    private final Command command;

    private final String name;

    /**
     * Given a command and a name wraps the command into a NamedCommand with the given name.
     * @param command Command to wrap.
     * @param name Name of the command.
     */
    public NamedCommandWraper(Command command, String name) {
        this.command = command;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(PrintStream printStream, InputStream inputStream, List<String> arguments) {
        command.execute(printStream, inputStream, arguments);
    }
}
