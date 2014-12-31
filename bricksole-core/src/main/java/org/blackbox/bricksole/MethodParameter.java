package org.blackbox.bricksole;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guillermoblascojimenez on 30/12/14.
 */
class MethodParameter {

    private final Class<?> type;

    private final List<Annotation> annotations;

    MethodParameter(Class<?> type, List<Annotation> annotations) {
        this.type = type;
        this.annotations = annotations;
    }

    public Class<?> getType() {
        return type;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public <T extends Annotation> T getAnnotation(final Class<T> annotationClass) {
        for (Annotation annotation : getAnnotations()) {
            if (annotation.annotationType().equals(annotationClass)) {
                return (T) annotation;
            }
        }
        return null;
    }

    public static List<MethodParameter> of(Method method) {
        assert method != null;
        Class<?>[] parameterTypes = method.getParameterTypes();
        Annotation[][] parametersAnnotations = method.getParameterAnnotations();

        List<MethodParameter> methodParameters = new ArrayList<>(parameterTypes.length);
        for (int parameterIndex = 0; parameterIndex < parameterTypes.length; parameterIndex++) {
            Class<?> parameterType = parameterTypes[parameterIndex];
            Annotation[] parameterAnnotation = parametersAnnotations[parameterIndex];
            assert parameterType != null;
            assert parameterAnnotation != null;
            methodParameters.add(new MethodParameter(parameterType, Arrays.asList(parameterAnnotation)));
        }

        return methodParameters;
    }
}
