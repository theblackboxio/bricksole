package org.blackbox.bricksole;

/**
 * Created by guillermoblascojimenez on 30/12/14.
 */
class CommandParameter {

    private final String name;
    private final boolean required;
    private final String defaultValue;
    private final Class<?> type;

    CommandParameter(String name, boolean required, String defaultValue, Class<?> type) {
        this.name = name;
        this.required = required;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean isNamed() {
        return name != null;
    }

    static CommandParameter of(MethodParameter methodParameter) {
        CommandParam commandParamAnnotation = methodParameter.getAnnotation(CommandParam.class);
        Class<?> parameterType = methodParameter.getType();
        if (commandParamAnnotation != null) {
            String parameterName = commandParamAnnotation.value();
            boolean required = commandParamAnnotation.required();
            String defaultValue = commandParamAnnotation.defaultValue();
            if (defaultValue.equals(B.COMMAND_PARAMETER_DEFAULT_VALUE)) {
                defaultValue = null;
            }
            return new CommandParameter(parameterName, required, defaultValue, parameterType);
        } else {
            return new CommandParameter(null, true, null, parameterType);
        }
    }

    public boolean hasDefaultValue() {
        return !B.COMMAND_PARAMETER_DEFAULT_VALUE.equals(this.defaultValue);
    }
}
