package org.blackbox.bricksole;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by guillermoblascojimenez on 31/12/14.
 */
final class Reflections {

    private Reflections() { }

    static List<Method> getMethodAnnotatedWith(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> methods = new LinkedList<>();
        for (Method method : clazz.getMethods()) {
            for (Annotation annotation1 : method.getAnnotations()) {
                if (annotation.equals(annotation1.annotationType())) {
                    methods.add(method);
                    break;
                }
            }
        }
        return methods;
    }
}
