# bricksole-core

## Structure

Bricksole core has three components:

* `Command` interface that every command should implements.

* `CommandContext` interface that represents a container of commands names mapped to command 
objects. The class `ConfigurableCommandContext` implements this interface.

* `Bricksole` class that loads spring, finds the `CommandContext` and dispatches the
commands.

`Bricksole` requires:

* the path of spring context as an XML file,

* the bean name of a declared `CommandContext` if there is ambiguity when asking for a bean instance 
of `CommandContext` in the container. 

With this two requirements `Bricksole` instance is ready to dispatch
command lines. For example:

    public static void main(String[] args) {
        String contextLocation = "classpath*:org/blackbox/bricksole/example/spring-context-declarative.xml";
        List<String> arguments = Arrays.asList(args);
        Bricksole bricksole = new Bricksole(contextLocation, "commandContext");
        bricksole.start();
        bricksole.dispatch(arguments);
    }
