package sg.kata.mower.core;

/**
 * Exception that represents errors occuring in the application during the parsing phase
 */
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
