package org.blackbox.bricksole;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Configurable command context that allows to load commands initially and to collect them from
 * spring bean container.
 */
public class ConfigurableCommandContext extends AbstractCommandContext {

    private final Collection<Object> initialCommands = new ArrayList<>();

    private final List<CommandInspector> commandInspectors = new ArrayList<>();

    /**
     * Creates a empty configurable command context. Uses as streams the provided by System.
     */
    public ConfigurableCommandContext() {
        super();
    }

    public ConfigurableCommandContext(Collection<Object> initialCommands) {
        this();
        this.initialCommands.addAll(initialCommands);
    }

    public ConfigurableCommandContext(Configuration configuration) {
        super(configuration.getPrintStream(), configuration.getInputStream());
        this.initialCommands.addAll(configuration.getCommands());
        this.commandInspectors.addAll(configuration.getInspectors());
    }

    public void configure() throws CommandInitializationException {
        for (CommandInspector commandInspector : commandInspectors) {
            Collection<Object> inspected  = commandInspector.inspect();
            initialCommands.addAll(inspected);
        }
        for (Object commands : initialCommands) {
            configureCommand(commands);
        }
        initialCommands.clear();
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
                Object oldCommand = this.commands.get(commandName);
                if (oldCommand != command) {
                    throw new CommandNameDuplicatedException("Duplicated command name " + commandName + " for different objects: " + oldCommand + ", " + command);
                }
                // else, duplicated but is the same object.
            } else {
                this.commands.put(commandName, callableCommand);
            }
        }

    }

}
