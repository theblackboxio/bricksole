package org.blackbox.bricksole.spring;

import org.blackbox.bricksole.CommandInspector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleCommandSpringInspectorTest {

    private CommandInspector inspector;

    @Before
    public void setup() {
        String contextLocation = "classpath:org/blackbox/bricksole/spring/spring-context.xml";

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(contextLocation);
        inspector = applicationContext.getBean("commandInspector", CommandInspector.class);
    }

    @Test
    public void testInspection() {
        Collection<Object> inspected = inspector.inspect();
        Assert.assertEquals(2, inspected.size());
        Collection<Class<?>> classes = new ArrayList<>();
        for (Object object : inspected) {
            classes.add(object.getClass());
        }
        Assert.assertEquals(2, classes.size());
        Assert.assertTrue(classes.contains(AnotherCommand.class));
        Assert.assertTrue(classes.contains(EchoCommand.class));

    }

}