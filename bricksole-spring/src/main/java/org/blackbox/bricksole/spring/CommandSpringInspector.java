package org.blackbox.bricksole.spring;

import org.blackbox.bricksole.CommandInspector;
import org.springframework.context.ApplicationContextAware;

/**
 * Command inspector where the context is the spring context.
 */
public interface CommandSpringInspector extends ApplicationContextAware, CommandInspector {

}
