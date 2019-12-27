package com.jonahseguin.drink.parametric;

import com.google.common.base.Preconditions;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.util.Set;

@Getter
public class DrinkBinding<T> {

    private final Class<T> type;
    private final Set<Class<? extends Annotation>> annotations;
    private final DrinkProvider<T> provider;

    public DrinkBinding(Class<T> type, Set<Class<? extends Annotation>> annotations, DrinkProvider<T> provider) {
        this.type = type;
        this.annotations = annotations;
        this.provider = provider;
    }

    public boolean canProvideFor(@Nonnull CommandParameter parameter) {
        Preconditions.checkNotNull(parameter, "Parameter cannot be null");
        // The parameter and binding need to have exact same annotations
        for (Annotation annotation : parameter.getClassifierAnnotations()) {
            if (!annotations.contains(annotation.annotationType())) {
                return false;
            }
        }
        return true;
    }

}
