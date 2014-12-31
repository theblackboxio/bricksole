package org.blackbox.bricksole;

/**
 * Created by guillermoblascojimenez on 31/12/14.
 */
public class WrongCommand {

    @Command("wrong")
    public void execute(@CommandParam("v") String verbose, String wrongPlacedParameter) {

    }
}
