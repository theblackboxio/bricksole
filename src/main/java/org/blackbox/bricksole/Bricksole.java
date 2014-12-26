package org.blackbox.bricksole;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * The Bricksole class loads Spring context, takes the command context instance and dispatches the
 * requested command.
 */
public class Bricksole implements Runnable {

    private final String contextLocation;
    private final List<String> arguments;
    private final String commandContextBeanId;

    public Bricksole(String contextLocation, String commandContextBeanId, List<String> arguments) {
        this.contextLocation = contextLocation;
        this.arguments = arguments;
        this.commandContextBeanId = commandContextBeanId;
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("Arguments should not be empty.");
        }
    }

    @Override
    public void run() {
        // Load spring context
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(this.contextLocation);
        // shutdown spring gracefully
        applicationContext.registerShutdownHook();
        // take the command context instance
        CommandContext commandContext = applicationContext.getBean(commandContextBeanId, CommandContext.class);

        // in case that is an ExplorerCommandContext it has to collect the command beans
        if (commandContext instanceof ExplorerCommandContext) {
            ((ExplorerCommandContext) commandContext).collectCommands();
        }

        // dispatch the requested command
        String commandName = arguments.get(0);
        List<String> commandArguments = arguments.subList(1, arguments.size());
        commandContext.execute(commandName, commandArguments);

    }

}
