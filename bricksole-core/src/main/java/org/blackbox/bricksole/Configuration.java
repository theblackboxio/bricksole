package org.blackbox.bricksole;

import com.google.common.base.Preconditions;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Configuration of a ConfigurableCommandContext.
 */
public class Configuration {

    private PrintStream printStream = System.out;
    private InputStream inputStream = System.in;
    private Collection<Object> commands = new ArrayList<>();
    private Collection<CommandInspector> inspectors = new ArrayList<>();

    public Configuration setPrintStream(PrintStream printStream) {
        Preconditions.checkNotNull(printStream);
        this.printStream = printStream;
        return this;
    }

    public Configuration setInputStream(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream);
        this.inputStream = inputStream;
        return this;
    }

    public Configuration setCommands(Object ... commands) {
        Collection<Object> commands2 = new ArrayList<>(Arrays.asList(commands));
        if (commands2.contains(null)) {
            throw new NullPointerException("Null command in command list");
        }
        this.commands.addAll(commands2);
        return this;
    }
    public Configuration setInspectors(CommandInspector ... inspectors) {
        Collection<CommandInspector> inspectors2 = new ArrayList<>(Arrays.asList(inspectors));
        if (inspectors2.contains(null)) {
            throw new NullPointerException("Null inspector in inspector list");
        }
        this.inspectors.addAll(inspectors2);
        return this;
    }

    PrintStream getPrintStream() {
        return printStream;
    }

    InputStream getInputStream() {
        return inputStream;
    }

    Collection<Object> getCommands() {
        return commands;
    }

    Collection<CommandInspector> getInspectors() {
        return inspectors;
    }
}

