package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Interface that any command should implement to be executed by the app.
 *
 * @author guillermoblascojimenez
 * @see org.blackbox.bricksole.NamedCommand
 */
public interface Command {

    /**
     * The execution of the command with a given print stream, that may be the std output,
     * the input stream, that may be the std input, and the passed arguments.
     *
     * @param printStream The stream where the command is able to print.
     * @param inputStream The stream where the command is able to read.
     * @param arguments The arguments passed by the user.
     */
    void execute(PrintStream printStream, InputStream inputStream, List<String> arguments);

}
