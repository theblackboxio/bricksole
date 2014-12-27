# bricksole

`bricksole` is a Spring based framework to develop command line applications. But, why `bricksole`?

* Command line java applications may be used to rapidly test and develop systems and infrastructure,

* Command line java applications follows some patterns that remembers to the web-mvc spring,

* Spring is a great framework,

* Fun

## Where bricksole will go?

Spring web mvc maps url via `@RequestMapping` to class' methods, and also maps the request parameters
to the method's parameters. Later some response is returned. When dealing with command line apps
the pattern is the same:

* a command name that works as an url,

* some parameters

* a return

Example:

    sh bricksole.sh <command name> <command parameters>
    >>> <some return>
    
The objective is to evolve `bricksole` until this parallelism meets a nice framework where you can
do things like that:

    public class Git {
    
        @CommandMapping("status")
        public void status() {...}
        
        @CommandMapping("commit')
        public void commit(
            @CommandParameter("-m") String message, 
            @CommandParameter("-a") boolean all) {
            ...
        }
    }

