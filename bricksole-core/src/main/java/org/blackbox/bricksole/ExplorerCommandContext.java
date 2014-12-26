package org.blackbox.bricksole;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;

/**
 * Created by guillermoblascojimenez on 26/12/14.
 */
public class ExplorerCommandContext extends AbstractCommandContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ExplorerCommandContext() {
        super();
    }

    public ExplorerCommandContext(PrintStream printStream, InputStream inputStream) {
        super(printStream, inputStream);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void collectCommands() {

        Map<String, Object> commands = applicationContext.getBeansWithAnnotation(NamedCommand.class);

        for (Object command : commands.values()) {
            String name = command.getClass().getAnnotation(NamedCommand.class).value();

            Command oldCommand = this.commands.putIfAbsent(name, (Command) command);
            if (oldCommand != null) {
                throw new IllegalStateException("Command with name " + name + " duplicated.");
            }
        }
    }

}
