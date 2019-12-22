package dev.nestedvar.Quiver.api.annotation;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SerializedArrowDescription {
    public static final Pattern ID_PATTERN = Pattern.compile("[a-z][a-z0-9-_]{0,63}");

    private final String id;
    private final @Nullable String name;
    private final @Nullable String version;
    private final @Nullable String description;
    private final @Nullable String url;
    private final @Nullable List<String> authors;
    private final @Nullable List<Dependency> dependencies;
    private final String main;

    private SerializedArrowDescription(final String id, final String name, final String version, final String description,
                                       final String url, final List<String> authors, final List<Dependency> dependencies,
                                       final String main) {
        Preconditions.checkNotNull(id, "id");
        Preconditions.checkArgument(ID_PATTERN.matcher(id).matches(), "id is invalid");

        this.id = id;
        this.name = Strings.emptyToNull(name);
        this.version = Strings.emptyToNull(version);
        this.description = Strings.emptyToNull(description);
        this.url = Strings.emptyToNull(url);
        this.authors = authors == null || authors.isEmpty() ? ImmutableList.of() : authors;
        this.dependencies = dependencies == null || dependencies.isEmpty() ? ImmutableList.of() : dependencies;
        this.main = Preconditions.checkNotNull(main, "main");
    }

    static SerializedArrowDescription from(final Arrow arrow, final String qualifiedName) {
        final List<Dependency> dependencies = new ArrayList<>();
        Arrays.stream(arrow.dependencies()).forEach(dependency -> dependencies.add(
                new Dependency(dependency.id(), dependency.optional())
        ));

        return new SerializedArrowDescription(arrow.id(), arrow.name(), arrow.version(), arrow.description(),
                arrow.url(), Arrays.stream(arrow.authors()).filter(author -> !author.isEmpty())
                .collect(Collectors.toList()), dependencies, qualifiedName);
    }

    public String getId() {
        return this.id;
    }

    public @Nullable String getName() {
        return this.name;
    }

    public @Nullable String getVersion() {
        return this.version;
    }

    public @Nullable String getDescription() {
        return this.description;
    }

    public @Nullable String getUrl() {
        return this.url;
    }

    public List<String> getAuthors() {
        return this.authors == null ? ImmutableList.of() : this.authors;
    }

    public List<Dependency> getDependencies() {
        return this.dependencies == null ? ImmutableList.of() : this.dependencies;
    }

    public String getMain() {
        return this.main;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final SerializedArrowDescription serializedArrow = (SerializedArrowDescription) object;
        return Objects.equals(this.id, serializedArrow.id)
                && Objects.equals(this.name, serializedArrow.name)
                && Objects.equals(this.version, serializedArrow.version)
                && Objects.equals(this.description, serializedArrow.description)
                && Objects.equals(this.url, serializedArrow.url)
                && Objects.equals(this.authors, serializedArrow.authors)
                && Objects.equals(this.dependencies, serializedArrow.dependencies)
                && Objects.equals(this.main, serializedArrow.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.version,
                this.description, this.url, this.authors, this.dependencies);
    }

    @Override
    public String toString() {
        return String.format(
                "SerializedArrowDescription{id='%s', name='%s', version='%s', description='%s', " +
                        "url='%s', authors='%s', dependencies='%s', main='%s'}",
                this.id, this.name, this.version, this.description, this.url, this.authors,
                this.description, this.main
        );
    }

    public static final class Dependency {
        private final String id;
        private final boolean optional;

        public Dependency(final String id, final boolean optional) {
            this.id = id;
            this.optional = optional;
        }

        public String getId() {
            return this.id;
        }

        public boolean isOptional() {
            return this.optional;
        }

        @Override
        public boolean equals(final Object object) {
            if (this == object) {
                return true;
            }

            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            final Dependency dependency = (Dependency) object;
            return this.optional == dependency.optional
                    && Objects.equals(this.id, dependency.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, optional);
        }

        @Override
        public String toString() {
            return String.format(
                "Dependency{id='%s', optional='%s'}", this.id, this.optional
            );
        }
    }
}
