package dev.nestedvar.Quiver.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Represents metadata for an Arrow
 */
public interface ArrowDescription {
    Pattern ID_PATTERN = Pattern.compile("[a-z][a-z0-9-_]{0,63}");

    String getId();

    default Optional<String> getName() {
        return Optional.empty();
    }

    default Optional<String> getVersion() {
        return Optional.empty();
    }

    default Optional<String> getDescription() {
        return Optional.empty();
    }

    default Optional<String> getUrl() {
        return Optional.empty();
    }

    default List<String> getAuthors() {
        return ImmutableList.of();
    }

    default Collection<ArrowDependency> getDependencies() {
        return ImmutableSet.of();
    }

    default Optional<ArrowDependency> getDependency(final String id) {
        return Optional.empty();
    }

    default Optional<Path> getSource() {
        return Optional.empty();
    }
}
