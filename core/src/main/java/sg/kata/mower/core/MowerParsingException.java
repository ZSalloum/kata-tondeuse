package sg.kata.mower.core;

public class MowerParsingException extends MowerException{
    public MowerParsingException() {
        super();
    }

    public MowerParsingException(String message) {
        super(message);
    }

    public MowerParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
