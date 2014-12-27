package org.blackbox.bricksole.example;

import org.blackbox.bricksole.NamedCommand;
import org.blackbox.bricksole.command.EchoCommand;

/**
 * Example Command that prints the version of the app.
 * The class is annotated as "version" so it can be called with this name.
 */
@NamedCommand("version")
public class VersionCommand extends EchoCommand {
    public VersionCommand(String version) {
        super("Version: " + version);
    }
}
