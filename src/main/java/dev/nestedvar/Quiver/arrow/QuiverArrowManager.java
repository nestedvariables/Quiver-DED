package dev.nestedvar.Quiver.arrow;

import com.google.common.base.Preconditions;
import dev.nestedvar.Quiver.api.ArrowContainer;
import dev.nestedvar.Quiver.api.ArrowDescription;
import dev.nestedvar.Quiver.api.ArrowManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class QuiverArrowManager implements ArrowManager {
    private static final Logger logger = LogManager.getLogger(QuiverArrowManager.class);

    private final Map<String, ArrowContainer> arrows = new HashMap<>();
    private final Map<Object, ArrowContainer> arrowInstances = new IdentityHashMap<>();

    private void registerArrow(final ArrowContainer arrowContainer) {
        arrows.put(arrowContainer.getDescription().getId(), arrowContainer);
        Optional<?> instance = arrowContainer.getInstance();
        instance.ifPresent(object -> arrowInstances.put(object, arrowContainer));
    }

    public void loadPlugins(final Path directory) throws IOException {
        Preconditions.checkNotNull(directory, "directory");
        Preconditions.checkArgument(directory.toFile().isDirectory(),
                "provided path is not a directory");

        final List<ArrowDescription> foundArrows = new ArrayList<>();

    }

    @Override
    public Optional<ArrowContainer> fromInstance(Object instance) {
        return Optional.empty();
    }

    @Override
    public Optional<ArrowContainer> getPlugin(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<ArrowContainer> getPlugins() {
        return null;
    }

    @Override
    public boolean isLoaded(String id) {
        return false;
    }

    @Override
    public void addToClasspath(Object instance, Path path) {

    }
}
