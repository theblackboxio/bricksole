package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;

/**
 * Created by guillermoblascojimenez on 26/12/14.
 */
public class DeclarativeCommandContext extends AbstractCommandContext<Command> implements CommandContext {

    public DeclarativeCommandContext(Map<String, ? extends Command> commands) {
        super();
        super.commands.putAll(commands);
    }

    public DeclarativeCommandContext(PrintStream printStream, InputStream inputStream, Map<String, ? extends Command> commands) {
        super(printStream, inputStream);
        super.commands.putAll(commands);
    }

}
