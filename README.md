# bricksole

`bricksole` is a Spring based framework to develop command line applications. We think that `bricksole` rocks, because:

* Command line java applications may be used to rapidly test and develop systems and infrastructure,

* Command line java applications follow some patterns that remember to the web-mvc spring pattern,

* Spring is a great framework,

* It's fun!

## Modules

### bricksole-core

Contains the core of bricksole. Can be used programatically as a standalone library.

### bricksole-spring

Contains the classes to build a Spring application be based on the bricksole-core module.

## Example of use

    public static void main(String[] args) {
        String contextLocation = "classpath*:org/blackbox/bricksole/example/spring-context-declarative.xml";
        List<String> arguments = Arrays.asList(args);
        Bricksole bricksole = new Bricksole(contextLocation, "commandContext");
        bricksole.start();
        bricksole.dispatch(arguments);
    }
    
To declare commands, simply add a bean of a class. Something like this:
    
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
