package dev.nestedvar.Quiver.api.annotation;

import com.google.gson.Gson;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
import java.util.Set;

public class ArrowAnnotationProcessor extends AbstractProcessor {
    private ProcessingEnvironment environment;
    private String arrowClassFound;
    private boolean multiArrowWarning;

    @Override
    public synchronized void init(final ProcessingEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        for (final Element element : roundEnv.getElementsAnnotatedWith(Arrow.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                environment.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(
                        "Only classes can be annotated with %s.", Arrow.class.getCanonicalName()
                ));
                return false;
            }

            final Name qualifiedName = ((TypeElement) element).getQualifiedName();
            if (Objects.equals(arrowClassFound, qualifiedName.toString())) {
                if (!multiArrowWarning) {
                    environment.getMessager().printMessage(Diagnostic.Kind.WARNING, String.format(
                            "Quiver doesn't support multiple plugins- proceeding with " +
                            "%s as the main class.", arrowClassFound
                    ));
                    multiArrowWarning = true;
                }
                return false;
            }

            final Arrow arrow = element.getAnnotation(Arrow.class);
            if (!SerializedArrowDescription.ID_PATTERN.matcher(arrow.id()).matches()) {
                environment.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(
                        "Invalid ID for plugin %s. IDs must be alphanumeric.", qualifiedName
                ));
                return false;
            }

            final SerializedArrowDescription serializedArrow = SerializedArrowDescription
                    .from(arrow, qualifiedName.toString());
            try {
                final FileObject fileObject = environment.getFiler()
                        .createResource(StandardLocation.CLASS_OUTPUT, "", "arrow.json");
                try (final Writer writer = new BufferedWriter(fileObject.openWriter())) {
                    new Gson().toJson(serializedArrow, writer);
                }
                arrowClassFound = qualifiedName.toString();
            } catch (IOException exception) {
                environment.getMessager().printMessage(Diagnostic.Kind.ERROR, "Unable to generate plugin file.");
            }
        }
        return false;
    }
}
