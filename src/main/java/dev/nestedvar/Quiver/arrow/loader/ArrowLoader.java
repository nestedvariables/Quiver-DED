package dev.nestedvar.Quiver.arrow.loader;

import dev.nestedvar.Quiver.api.ArrowContainer;
import dev.nestedvar.Quiver.api.ArrowDescription;

import java.nio.file.Path;

public interface ArrowLoader {
    ArrowDescription loadPlugin(final Path source) throws Exception;
    ArrowContainer createPlugin(final ArrowDescription arrow) throws Exception;
}
