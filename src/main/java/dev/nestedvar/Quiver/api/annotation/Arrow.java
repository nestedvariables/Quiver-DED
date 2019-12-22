package dev.nestedvar.Quiver.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Arrow {
    String id();
    String name() default "";
    String version() default "";
    String description() default "";
    String url() default "";
    String[] authors() default "";
    Dependency[] dependencies() default {};
}
