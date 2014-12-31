package org.blackbox.bricksole;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by guillermoblascojimenez on 30/12/14.
 */
public class ConfigurableCommandContextTest {

    private ConfigurableCommandContext context;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setup() throws CommandInitializationException {
        outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        List<Object> commands = Arrays.asList(new EchoCommand(), new AnotherCommand());
        context = new ConfigurableCommandContext(new Configuration()
                .setPrintStream(printStream)
                .setCommands(new EchoCommand(), new AnotherCommand()));
        context.configure();
        Assert.assertTrue(context.isConfigured());
    }

    @Test(expected = CommandNameDuplicatedException.class)
    public void testDuplicated() throws CommandInitializationException {
        List<Object> commands = Arrays.<Object>asList(new EchoCommand(), new EchoCommand());
        ConfigurableCommandContext context2 = new ConfigurableCommandContext(commands);
        context2.configure();
    }

    @Test(expected = CommandInitializationParseException.class)
    public void testParseException() throws CommandInitializationException {
        List<Object> commands = Arrays.<Object>asList(new WrongCommand());
        ConfigurableCommandContext context2 = new ConfigurableCommandContext(commands);
        context2.configure();
    }

    @Test
    public void testCall() throws CommandContextException {
        Assert.assertTrue(context.isConfigured());
        String message = "some message";
        context.execute("echo:message", Arrays.asList(message));
        String result = outputStream.toString();
        Assert.assertEquals(message + "\n", result);
        outputStream.reset();

        context.execute("another:execute", Collections.<String>emptyList());
        result = outputStream.toString();
        Assert.assertEquals("Another execution\n", result);
        outputStream.reset();
    }

    @Test
    public void testCall2() throws CommandContextException {
        Assert.assertTrue(context.isConfigured());
        String message = "some message";
        context.execute("echo:message2", Arrays.asList("-v", message));
        String result = outputStream.toString();
        Assert.assertEquals("the verbose is: "  + message + "\n", result);
        outputStream.reset();
    }

    @Test(expected = CommandNotFoundException.class)
    public void testNotFound() throws CommandNotFoundException {
        context.execute("methodThatNotExists", Collections.<String>emptyList());
    }

    @Test(expected = CommandCallException.class)
    public void testMalformed() throws CommandCallException {
        context.execute("echo:message2",  Arrays.asList("-v"));
    }

    @Test(expected = RequiredParameterNotFoundException.class)
    public void testRequiredNotFound() throws CommandCallException {
        context.execute("echo:message2",  Collections.<String>emptyList());
    }

    @Test(expected = RequiredParameterNotFoundException.class)
    public void testRequiredNotFound2() throws CommandCallException {
        context.execute("echo:message",  Collections.<String>emptyList());
    }



}
