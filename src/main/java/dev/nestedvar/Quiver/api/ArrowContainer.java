package dev.nestedvar.Quiver.api;

import java.util.Optional;

/**
 * A wrapper around a loaded Arrow
 */
public interface ArrowContainer {
    ArrowDescription getDescription();

    default Optional<?> getInstance() {
        return Optional.empty();
    }
}
