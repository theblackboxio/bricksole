package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Configurable command context that allows to load commands initially and to collect them from
 * spring bean container.
 */
public class ConfigurableCommandContext extends AbstractCommandContext {

    private Collection<Object> initialCommands = new ArrayList<>();

    /**
     * Creates a empty configurable command context. Uses as streams the provided by System.
     */
    public ConfigurableCommandContext() {
        super();
    }

    /**
     * Creates an empty configurable command context with the given streams.
     *
     * @param printStream
     * @param inputStream
     */
    public ConfigurableCommandContext(PrintStream printStream, InputStream inputStream) {
        super(printStream, inputStream);
    }

    public ConfigurableCommandContext(Collection<Object> initialCommands) {
        this();
        this.initialCommands.addAll(initialCommands);
    }

    public ConfigurableCommandContext(PrintStream printStream, InputStream inputStream, Collection<Object> initialCommands) {
        this(printStream, inputStream);
        this.initialCommands.addAll(initialCommands);
    }

    public void configure() throws CommandInitializationException {
        for (Object commands : initialCommands) {
            configureCommand(commands);
        }
        initialCommands = Collections.emptyList();
        super.configure();
    }

    private void configureCommand(Object command) throws CommandInitializationException {

        assert command != null;
        Class<?> commandClass = command.getClass();

        Command topLevelAnnotation = commandClass.getAnnotation(Command.class);
        String topLevelName;
        if (topLevelAnnotation == null) {
            topLevelName = "";
        } else {
            topLevelName = topLevelAnnotation.value();
        }

        String prefix = (topLevelName.length() == 0) ? "" : topLevelName + ":";

        for (Method commandMethod : Reflections.getMethodAnnotatedWith(commandClass, Command.class)) {
            Command commandAnnotation = commandMethod.getAnnotation(Command.class);
            String commandName = prefix + commandAnnotation.value();
            CallableCommand callableCommand = CallableCommand.create(command, commandMethod);
            if (this.commands.containsKey(commandName)) {
                throw new CommandNameDuplicatedException("Duplicated command name " + commandName);
            } else {
                this.commands.put(commandName, callableCommand);
            }
        }

    }

}
