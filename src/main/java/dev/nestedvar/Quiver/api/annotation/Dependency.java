package dev.nestedvar.Quiver.api.annotation;

public @interface Dependency {
    String id();
    boolean optional() default false;
}
