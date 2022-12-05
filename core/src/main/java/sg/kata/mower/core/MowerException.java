package sg.kata.mower.core;

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
