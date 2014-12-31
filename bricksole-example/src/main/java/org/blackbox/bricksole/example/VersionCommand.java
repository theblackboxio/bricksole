package org.blackbox.bricksole.example;

import org.blackbox.bricksole.Command;

/**
 * Example Command that prints the version of the app.
 * The class is annotated as "version" so it can be called with this name.
 */

public class VersionCommand {

    private final String version;

    public VersionCommand(String version) {
        this.version = version;
    }

    @Command("version")
    public String printVersion() {
        return "Version: " + version;
    }
}
