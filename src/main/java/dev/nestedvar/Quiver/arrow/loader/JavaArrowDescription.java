package dev.nestedvar.Quiver.arrow.loader;

import com.google.common.base.Preconditions;
import dev.nestedvar.Quiver.api.ArrowDependency;
import dev.nestedvar.Quiver.arrow.QuiverArrowDescription;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class JavaArrowDescription extends QuiverArrowDescription {
    private final Class<?> mainClass;

    JavaArrowDescription(final String id, final @Nullable String name, final @Nullable String version,
                         final @Nullable String description, final @Nullable String url,
                         final @Nullable List<String> authors, final Collection<ArrowDependency> dependencies,
                         final Path source, final Class<?> mainClass) {
        super(id, name, version, description, url, authors, dependencies, source);
        this.mainClass = Preconditions.checkNotNull(mainClass);
    }

    Class<?> getMainClass() {
        return this.mainClass;
    }
}
