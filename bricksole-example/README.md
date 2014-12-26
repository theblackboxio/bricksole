# Example

## Compilation

Execute maven goal `package` to create the shell scripts.

## Execution of example

Find in dir `target/appassembler/bin` the built apps. Here some examples:

    sh target/appassembler/bin/DeclarativeApp.sh version
    sh target/appassembler/bin/ExplorerApp.sh version
    
    git commit -m 'removed NamedCommand interface and turned it into an annotation, added shell scripts for both examples'