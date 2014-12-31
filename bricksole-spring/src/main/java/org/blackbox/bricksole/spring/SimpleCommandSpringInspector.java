package org.blackbox.bricksole.spring;

import org.blackbox.bricksole.Command;
import org.blackbox.bricksole.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Given the spring application context inspects for all beans that have at least one method
 * annotated with command annotation.
 */
public class SimpleCommandSpringInspector implements CommandSpringInspector {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Collection<Object> inspect() {
        Collection<Object> commands = new LinkedList<>();
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(beanName);
            Class<?> beanClass = bean.getClass();
            if (Reflections.hasMethodAnnotatedWith(beanClass, Command.class)) {
                commands.add(bean);
            }
        }
        return commands;
    }

}
