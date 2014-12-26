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
public class ExplorerCommandContext extends AbstractCommandContext<NamedCommand> implements ApplicationContextAware {

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

        Map<String, NamedCommand> commands = applicationContext.getBeansOfType(NamedCommand.class);

        for (NamedCommand command : commands.values()) {
            NamedCommand oldCommand = this.commands.putIfAbsent(command.getName(), command);
            if (oldCommand != null) {
                throw new IllegalStateException("Command with name " + command.getName() + " duplicated.");
            }
        }
    }

}
