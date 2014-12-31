package org.blackbox.bricksole;

/**
 * Created by guillermoblascojimenez on 31/12/14.
 */
@Command("another")
public class AnotherCommand {

    @Command("execute")
    public String execute() {
        return "Another execution";
    }
}
