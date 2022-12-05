package sg.kata.mower.core;

/**
 * Exception that represents errors occuring in the application at runtime
 */
public class MowerException extends RuntimeException{
    public MowerException() {
        super();
    }

    public MowerException(String message) {
        super(message);
    }

    public MowerException(String message, Throwable cause) {
        super(message, cause);
    }
}
