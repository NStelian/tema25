package ro.itschool.tema25.model.exceptions;

public class IncorrectOrderFormatException extends RuntimeException {
    public IncorrectOrderFormatException(String message) {
        super(message);
    }
}
