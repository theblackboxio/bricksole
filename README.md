# bricksole

`bricksole` is a Spring based framework to develop command line applications. But, why `bricksole`?

* Command line java applications may be used to rapidly test and develop systems and infrastructure,

* Command line java applications follows some patterns that remembers to the web-mvc spring,

* Spring is a great framework,

* Fun

## Modules

### bricksole-core

Contains the core of bricksole and can be used programatically standalone.

### bricksole-spring

Contains the classes to build a Spring application based on bricksole-core module.

## Example of use

    public static void main(String[] args) {
        String contextLocation = "classpath*:org/blackbox/bricksole/example/spring-context-declarative.xml";
        List<String> arguments = Arrays.asList(args);
        Bricksole bricksole = new Bricksole(contextLocation, "commandContext");
        bricksole.start();
        bricksole.dispatch(arguments);
    }
    
And to declare commands simply add a bean of a class like this:
    
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