package org.blackbox.bricksole.example;

import org.blackbox.bricksole.NamedCommand;
import org.blackbox.bricksole.command.EchoCommand;

/**
 * Created by guillermoblascojimenez on 26/12/14.
 */
@NamedCommand("version")
public class VersionCommand extends EchoCommand {
    public VersionCommand(String version) {
        super("Version: " + version);
    }
}
