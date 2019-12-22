package dev.nestedvar.Quiver.api;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class ArrowDependency {
    private final String id;
    private final @Nullable String version;
    private final boolean optional;

    public ArrowDependency(final String id, final @Nullable String version, final boolean optional) {
        this.id = Preconditions.checkNotNull(id, "id");
        Preconditions.checkArgument(!id.isEmpty(), "id cannot be empty");
        this.version = Strings.emptyToNull(version);
        this.optional = optional;
    }

    public String getId() {
        return id;
    }

    public Optional<String> getVersion() {
        return Optional.ofNullable(this.version);
    }

    public boolean isOptional() {
        return this.optional;
    }

    @Override
    public boolean equals(final @Nullable Object object) {
        if (this == object) {
            return true;
        }

        if (object == null | getClass() != object.getClass()) {
            return false;
        }

        final ArrowDependency dependency = (ArrowDependency) object;
        return this.optional == dependency.optional
                && Objects.equals(this.id, dependency.id)
                && Objects.equals(this.version, dependency.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.version, this.optional);
    }

    @Override
    public String toString() {
        return String.format(
                "ArrowDependency{id='%s', version='%s', optional='%s'}",
                this.id, this.version, this.optional
        );
    }
}
