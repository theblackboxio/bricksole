package org.blackbox.bricksole.example;

import org.blackbox.bricksole.Bricksole;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guillermoblascojimenez on 26/12/14.
 */
public class ExplorerApp {

    public static void main(String[] args) {
        String contextLocation = "classpath*:org/blackbox/bricksole/example/spring-context-explorer.xml";
        List<String> arguments = Arrays.asList(args);
        Bricksole bricksole = new Bricksole(contextLocation, "commandContext", arguments);
        bricksole.run();
    }
}
