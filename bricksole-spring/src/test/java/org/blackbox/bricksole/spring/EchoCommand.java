package org.blackbox.bricksole.spring;

import org.blackbox.bricksole.Command;
import org.blackbox.bricksole.CommandParam;

/**
 * Simple and generic command to echo strings.
 */
@Command("echo")
public class EchoCommand {

    @Command("message")
    public String echoMessage(String message) {
        if (message == null) {
            return "No message has been passed";
        } else {
            return message;
        }
    }

    @Command("message2")
    public String echoMessage2(@CommandParam(value="v") String verbose) {
        assert verbose != null;
        return "the verbose is: " + verbose;
    }

}
