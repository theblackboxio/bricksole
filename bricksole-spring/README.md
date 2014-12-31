# bricksole-spring

This module contains two elements:

* `Bricksole` class that loads spring, finds the `CommandContext` and dispatches the
commands.

* `CommandSpringInspector` interface that inspects the spring context looking for commands to load.

## Usage

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
