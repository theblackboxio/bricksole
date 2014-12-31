package org.blackbox.bricksole;

import java.util.Collection;

/**
 * Interface that inspects a context for commands.
 */
public interface CommandInspector {

    /**
     * Inspects the context and returns a collection of command objects.
     * @return The collection of command objects inspected.
     */
    Collection<Object> inspect();

}
