package org.blackbox.bricksole;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Configurable command context that allows to load commands initially and to collect them from
 * spring bean container.
 */
public class ConfigurableCommandContext extends AbstractCommandContext implements ApplicationContextAware {

    /**
     * Collection policies.
     */
    public enum CollectPolicy {
        /** This policy shall collect all beans annotated with @NamedCommand */
        COLLECT_ANNOTATED_WITH_NAME,
        /** This policy shall collect nothing */
        COLLECT_NOTHING
    }

    private CollectPolicy collectPolicy = CollectPolicy.COLLECT_NOTHING;

    private ApplicationContext applicationContext;

    private final Map<String, Command> initialCommands = new HashMap<>();

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

    public ConfigurableCommandContext(Map<String, ? extends Command> initialCommands) {
        this();
        this.initialCommands.putAll(initialCommands);
    }

    public ConfigurableCommandContext(PrintStream printStream, InputStream inputStream, Map<String, ? extends Command> initialCommands) {
        this(printStream, inputStream);
        this.initialCommands.putAll(initialCommands);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public CollectPolicy getCollectPolicy() {
        return collectPolicy;
    }

    public void setCollectPolicy(CollectPolicy collectPolicy) {
        this.collectPolicy = collectPolicy;
    }

    public void configure() throws CommandNameDuplicatedException {
        super.addAllCommands(initialCommands);
        switch (collectPolicy) {

            case COLLECT_ANNOTATED_WITH_NAME:
                Map<String, Object> commands = applicationContext.getBeansWithAnnotation(NamedCommand.class);

                for (Object command : commands.values()) {
                    String name = command.getClass().getAnnotation(NamedCommand.class).value();
                    Command oldCommand = this.commands.putIfAbsent(name, (Command) command);
                    if (oldCommand != null) {
                        throw new CommandNameDuplicatedException("Multiple commands for name \"" + name + "\" :" + command.getClass() + ", " + oldCommand.getClass());
                    }
                }
                break;
            case COLLECT_NOTHING:
            default:
                break;
        }
    }

}
