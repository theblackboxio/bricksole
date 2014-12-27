package org.blackbox.bricksole;

import com.google.common.base.Preconditions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * The Bricksole class loads Spring context, takes the command context instance and dispatches the
 * requested command.
 */
public class Bricksole {

    private final String contextLocation;
    private final String commandContextBeanId;
    private ConfigurableApplicationContext applicationContext;
    private CommandContext commandContext;

    public Bricksole(String contextLocation, String commandContextBeanId) {

        Preconditions.checkNotNull(contextLocation);
        Preconditions.checkNotNull(commandContextBeanId);

        this.contextLocation = contextLocation;
        this.commandContextBeanId = commandContextBeanId;
    }

    public Bricksole(String contextLocation) {

        Preconditions.checkNotNull(contextLocation);

        this.contextLocation = contextLocation;
        this.commandContextBeanId = null;
    }

    public void start() {
        Preconditions.checkState(applicationContext == null, "Bricksole has been already started.");
        // Load spring context
        applicationContext = new ClassPathXmlApplicationContext(this.contextLocation);
        // shutdown spring gracefully
        applicationContext.registerShutdownHook();

        if (commandContextBeanId != null) {
            commandContext = applicationContext.getBean(commandContextBeanId, CommandContext.class);
        } else {
            commandContext = applicationContext.getBean(CommandContext.class);
        }
        Preconditions.checkArgument(commandContext != null, "Command context bean id references null.");
    }

    public void dispatch(List<String> arguments) {
        Preconditions.checkState(commandContext != null, "Bricksole has not been started so it can not dispatch arguments.");
        Preconditions.checkArgument(!arguments.isEmpty(), "Arguments can not be an empty list.");
        // take the command context instance
        // dispatch the requested command
        String commandName = arguments.get(0);
        List<String> commandArguments = arguments.subList(1, arguments.size());
        try {
            commandContext.execute(commandName, commandArguments);
        } catch (CommandNotFoundException e) {
            e.printStackTrace(commandContext.getPrintStream());
            System.exit(-1);
        }
    }

    public static void dispatch(String contextLocation, String commandContextBeanId, List<String> arguments) {
        Bricksole bricksole = new Bricksole(contextLocation, commandContextBeanId);
        bricksole.start();
        bricksole.dispatch(arguments);
    }

}
