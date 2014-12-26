package org.blackbox.bricksole.command;

import org.blackbox.bricksole.Command;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by guillermoblascojimenez on 25/12/14.
 */
public class EchoCommand implements Command {

    private final String content;

    public EchoCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(PrintStream printStream, InputStream inputStream, List<String> arguments) {
        printStream.println(content);
    }
}
