package org.blackbox.bricksole;

import com.google.common.base.Preconditions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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

    /**
     * Full constructor. The contextLocation have to be an xml resource of a Spring context
     * application.
     * @param contextLocation Xml resource or file defining a Spring context application.
     * @param commandContextBeanId Id of the command context bean.
     */
    public Bricksole(String contextLocation, String commandContextBeanId) {

        Preconditions.checkNotNull(contextLocation);
        Preconditions.checkNotNull(commandContextBeanId);

        this.contextLocation = contextLocation;
        this.commandContextBeanId = commandContextBeanId;
    }

    /**
     * The command context id shall be inferred via class. If there are multiple beans for
     * CommandContext type then an exception is thrown.
     * @param contextLocation Xml resource or file defining a Spring context application.
     */
    public Bricksole(String contextLocation) {

        Preconditions.checkNotNull(contextLocation);

        this.contextLocation = contextLocation;
        this.commandContextBeanId = null;
    }

    /**
     * Starts the bricksole application. Loads spring and configures the command context.
     */
    public void start() {
        Preconditions.checkState(applicationContext == null, "Bricksole has been already started.");
        // Load spring context
        if (this.contextLocation.startsWith("classpath")) {
            applicationContext = new ClassPathXmlApplicationContext(this.contextLocation);
        } else {
            applicationContext = new FileSystemXmlApplicationContext(this.contextLocation);
        }
        // shutdown spring gracefully
        applicationContext.registerShutdownHook();

        if (commandContextBeanId != null) {
            commandContext = applicationContext.getBean(commandContextBeanId, CommandContext.class);
        } else {
            commandContext = applicationContext.getBean(CommandContext.class);
        }
        Preconditions.checkArgument(commandContext != null, "Command context bean id references null.");
    }

    /**
     * Dispatches a new command line arguments.
     * @param arguments
     */
    public void dispatch(List<String> arguments) {
        Preconditions.checkState(commandContext != null, "Bricksole has not been started so it can not dispatch arguments.");
        Preconditions.checkArgument(!arguments.isEmpty(), "Arguments can not be an empty list.");
        // take the command context instance
        // dispatch the requested command
        String commandName = arguments.get(0);
        List<String> commandArguments = arguments.subList(1, arguments.size());
        commandContext.execute(commandName, commandArguments);
    }

    /**
     * Standalone execution of a command line arguments. Initializes bricksole, starts it and
     * dispatches arguments.
     *
     * @param contextLocation Xml resource defining a Spring context application.
     * @param arguments Command line arguments to execute.
     */
    public static void dispatch(String contextLocation, List<String> arguments) {
        Bricksole bricksole = new Bricksole(contextLocation);
        bricksole.start();
        bricksole.dispatch(arguments);
    }

    /**
     * Standalone execution of a command line arguments. Initializes bricksole, starts it and
     * dispatches arguments.
     *
     * @param contextLocation Xml resource defining a Spring context application.
     * @param commandContextBeanId Id of the command context bean.
     * @param arguments Command line arguments to execute.
     */
    public static void dispatch(String contextLocation, String commandContextBeanId, List<String> arguments) {
        Bricksole bricksole = new Bricksole(contextLocation, commandContextBeanId);
        bricksole.start();
        bricksole.dispatch(arguments);
    }

}
