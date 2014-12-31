package org.blackbox.bricksole;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by guillermoblascojimenez on 30/12/14.
 */
class CallableCommand {

    private final String name;

    private final Object commandObject;

    private final Method commandMethod;

    private final List<CommandParameter> commandParameters;

    CallableCommand(String name, Object commandObject, Method commandMethod, List<CommandParameter> commandParameters) {
        this.name = name;
        this.commandObject = commandObject;
        this.commandMethod = commandMethod;
        this.commandParameters = commandParameters;
    }

    public String getName() {
        return name;
    }

    public Object getCommandObject() {
        return commandObject;
    }

    public Method getCommandMethod() {
        return commandMethod;
    }

    public void execute(PrintStream printStream, InputStream inputStream, List<String> arguments) {

        Object[] processedArguments = processArguments(arguments);

        Object result = null;
        try {
            result = commandMethod.invoke(commandObject, processedArguments);
        } catch (Exception e) {
            throw new CommandCallException(e);
        }

        if (result != null) {
            printStream.println(result.toString());
        }

    }

    private Object[] processArguments(List<String> arguments) {
        arguments = new ArrayList<>(arguments); // copy, since we are going to manipulate the list.

        int argumentIndex = 0;
        int parameterIndex = 0;
        Object[] processedArguments = new Object[commandParameters.size()];

        for (CommandParameter commandParameter : commandParameters) {
            if (commandParameter.isNamed()) {
                String parameterName = commandParameter.getName();
                String parameterKey = "-" + parameterName;
                int indexOfCommandKey = arguments.indexOf(parameterKey);
                if (indexOfCommandKey >= 0) {
                    // present
                    if (arguments.size() == indexOfCommandKey + 1) {
                        throw new CommandCallException("Found parameter key " + parameterKey + " but not value.");
                    }
                    String argument = arguments.get(indexOfCommandKey + 1);
                    arguments.remove(indexOfCommandKey);// remove key
                    arguments.remove(indexOfCommandKey);// remove value
                    processedArguments[parameterIndex] = argument;
                    parameterIndex++;
                } else {
                    // not present
                    if (commandParameter.isRequired()) {
                        throw new RequiredParameterNotFoundException("Required parameter " + parameterName + " for command " + this.getName() + " not found.");
                    } else {
                        if (commandParameter.hasDefaultValue()) {
                            processedArguments[parameterIndex] = commandParameter.getDefaultValue();
                            parameterIndex++;
                        } else {
                            processedArguments[parameterIndex] = null;
                            parameterIndex++;
                        }
                    }
                }
            } else {
                assert commandParameter.isRequired();
                if (arguments.size() == argumentIndex) {
                    throw new RequiredParameterNotFoundException("Required unnamed parameter of index " + argumentIndex + " for command " + this.getName() + " not found.");
                }
                processedArguments[parameterIndex] = arguments.get(argumentIndex);
                arguments.remove(argumentIndex);
            }
            parameterIndex++;
        }

        return processedArguments;
    }

    static CallableCommand create(Object commandObject, Method commandMethod) throws CommandInitializationParseException {
        Command commandAnnotation = commandMethod.getAnnotation(Command.class);
        String commandName = commandAnnotation.value();
        List<CommandParameter> commandParameters = new LinkedList<>();

        boolean unnamedParametersDispatched = false;
        for (MethodParameter methodParameter : MethodParameter.of(commandMethod)) {
            CommandParameter commandParameter = CommandParameter.of(methodParameter);
            if (commandParameter.isNamed()) {
                unnamedParametersDispatched = true;
            } else if (unnamedParametersDispatched) {
                throw new CommandInitializationParseException("Wrong order of command parameters, unnamed commands should go first");
            }

            commandParameters.add(commandParameter);
        }

        return new CallableCommand(commandName, commandObject, commandMethod, commandParameters);
    }
}
