package org.blackbox.bricksole.command;

import org.blackbox.bricksole.Command;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Simple and generic command to echo strings.
 */
public class EchoCommand implements Command {

    private final String content;

    /**
     * Build the command with the string to be printed.
     * @param content The string content to be printed.
     */
    public EchoCommand(String content) {
        this.content = content;
    }

    /**
     * On command execution print the content.
     * @param printStream The stream where the command is able to print.
     * @param inputStream The stream where the command is able to read.
     * @param arguments The arguments passed by the user.
     */
    @Override
    public void execute(PrintStream printStream, InputStream inputStream, List<String> arguments) {
        printStream.println(content);
    }
}
