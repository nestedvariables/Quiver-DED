package dev.nestedvar.Quiver.arrow.loader;

import dev.nestedvar.Quiver.Quiver;
import dev.nestedvar.Quiver.api.ArrowContainer;
import dev.nestedvar.Quiver.api.ArrowDependency;
import dev.nestedvar.Quiver.api.ArrowDescription;
import dev.nestedvar.Quiver.api.annotation.SerializedArrowDescription;
import dev.nestedvar.Quiver.api.exception.InvalidArrowException;
import dev.nestedvar.Quiver.arrow.QuiverArrowDescription;

import java.awt.print.Paper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class QuiverArrowLoader implements ArrowLoader {
    private final Path workingDirectory;

    public QuiverArrowLoader(final Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public ArrowDescription loadPlugin(final Path source) throws Exception {
        final Optional<SerializedArrowDescription> serializedArrow = getSerializedArrowInfo(source);

        if (!serializedArrow.isPresent()) {
            throw new InvalidArrowException("Cannot find a valid arrow.json.");
        }

        SerializedArrowDescription description = serializedArrow.get();
        if (!SerializedArrowDescription.ID_PATTERN.matcher(description.getId()).matches()) {
            throw new InvalidArrowException(String.format("Plugin ID '%s' is invalid.", description.getId()));
        }

        Arr

        return null;
    }

    @Override
    public ArrowContainer createPlugin(final ArrowDescription arrow) throws Exception {
        return null;
    }

    private Optional<SerializedArrowDescription> getSerializedArrowInfo(final Path location) throws IOException {
        try (final JarInputStream inputStream = new JarInputStream(
                new BufferedInputStream(Files.newInputStream(location)))) {
            JarEntry entry;
            while ((entry = inputStream.getNextJarEntry()) != null) {
                if (entry.getName().equals("arrow.json")) {
                    try (final Reader arrowInfoReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                        return Optional.of(Quiver.GSON.fromJson(arrowInfoReader, SerializedArrowDescription.class));
                    }
                }
            }
            return Optional.empty();
        }
    }

    private QuiverArrowDescription createDescription(final SerializedArrowDescription description, final Path source,
                                                     final Class mainClass) {
        final Set<ArrowDependency> dependencies = new HashSet<>();
        description.getDependencies().forEach(dependency -> dependencies.add(toDependencyMeta(dependency)));

        return new JavaArrowDescription(
          description.getId(),
          description.getName(),
          description.getVersion(),
          description.getDescription(),
          description.getUrl(),
          description.getAuthors(),
          dependencies,
          source,
          mainClass
        );
    }

    private static ArrowDependency toDependencyMeta(final SerializedArrowDescription.Dependency dependency) {
        return new ArrowDependency(dependency.getId(), null, dependency.isOptional()); // TODO version matching in annotation
    }
}
