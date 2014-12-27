package org.blackbox.bricksole.example;

import org.blackbox.bricksole.Bricksole;

import java.util.Arrays;
import java.util.List;

/**
 * Example of how to execute Bricksole with a spring configuration that will explore beans
 * looking for named commands.
 */
public class ExplorerApp {

    public static void main(String[] args) {
        String contextLocation = "classpath*:org/blackbox/bricksole/example/spring-context-explorer.xml";
        List<String> arguments = Arrays.asList(args);
        Bricksole bricksole = new Bricksole(contextLocation, "commandContext");
        bricksole.start();
        bricksole.dispatch(arguments);
    }
}
