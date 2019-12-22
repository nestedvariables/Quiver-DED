package dev.nestedvar.Quiver.arrow;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import dev.nestedvar.Quiver.api.ArrowDependency;
import dev.nestedvar.Quiver.api.ArrowDescription;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuiverArrowDescription implements ArrowDescription {
    private final String id;
    private final @Nullable String name;
    private final @Nullable String version;
    private final @Nullable String description;
    private final @Nullable String url;
    private final List<String> authors;
    private final Map<String, ArrowDependency> dependencies;
    private final Path source;

    public QuiverArrowDescription(final String id, final @Nullable String name, final @Nullable String version,
                                  final @Nullable String description, final @Nullable String url,
                                  final @Nullable List<String> authors, Collection<ArrowDependency> dependencies, final Path source) {
        this.id = Preconditions.checkNotNull(id, "id");
        this.name = Strings.emptyToNull(name);
        this.version = Strings.emptyToNull(version);
        this.description = Strings.emptyToNull(description);
        this.url = Strings.emptyToNull(url);
        this.authors = authors == null ? ImmutableList.of() : ImmutableList.copyOf(authors);
        this.dependencies = Maps.uniqueIndex(dependencies, dependency -> dependency == null ? null : dependency.getId());
        this.source = source;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public Optional<String> getVersion() {
        return Optional.ofNullable(version);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    @Override
    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public Collection<ArrowDependency> getDependencies() {
        return dependencies.values();
    }

    @Override
    public Optional<ArrowDependency> getDependency(String id) {
        return Optional.ofNullable(dependencies.get(id));
    }

    @Override
    public Optional<Path> getSource() {
        return Optional.ofNullable(source);
    }

    @Override
    public String toString() {
        return String.format(
                "QuiverArrowDescription{id='%s', name='%s', version='%s', description='%s', url='%s', " +
                        "authors='%s', dependencies='%s', source='%s'}",
                this.id, this.name, this.version, this.description, this.url, this.authors, this.dependencies,
                this.source
        );
    }
}
