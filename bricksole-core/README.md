# bricksole-core

## Structure

Bricksole core has two components:

* Annotations related to command definition: `Command` and `CommandParam`

* `CommandContext` interface and its implementing class `ConfigurableCommandContext`

## Command declaration

To declare a command annotate methods like this:


    public class Git {
    
        @Command("status")
        public String status() {
            // return status
        }
        
        @Command("commit")
        public String commit(@CommandParam("m") String message) {
            // process commit with message
        }
    }
    
## Command context

To create a command context able to dispatch command requests create a `ConfigurableCommandContext`:

    CommandContext commandContext = new ConfigurableCommandContext(new Configuration()
        .setPrintStream(...) // default is System.out
        .setInputStream(...) // default is System.in
        .setCommands(new Git(), ...) // add here instances of command objects
        .setInspectors(...) // add here inspectors, like SimpleCommandSpringInspector
        );
        
This object have to be configured once created:

    commandContext.configure();
    
And then it can dispatch command lines:

    commandContext.execute(Arrays.asList(mainArgs));