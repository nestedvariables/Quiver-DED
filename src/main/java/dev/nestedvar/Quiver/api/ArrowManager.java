package dev.nestedvar.Quiver.api;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

public interface ArrowManager {
    Optional<ArrowContainer> fromInstance(final Object instance);

    Optional<ArrowContainer> getPlugin(final String id);

    Collection<ArrowContainer> getPlugins();

    boolean isLoaded(final String id);

    void addToClasspath(final Object instance, final Path path);
}
