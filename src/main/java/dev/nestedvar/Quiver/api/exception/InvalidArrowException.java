package dev.nestedvar.Quiver.api.exception;

public class InvalidArrowException extends Exception {
    public InvalidArrowException() {
        super();
    }

    public InvalidArrowException(final String message) {
        super(message);
    }

    public InvalidArrowException(final String message, final Throwable exception) {
        super(message, exception);
    }

    public InvalidArrowException(final Throwable exception) {
        super(exception);
    }
}
