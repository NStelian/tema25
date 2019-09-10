package ro.itschool.tema25.model.exceptions;

public class PositiveStockException extends RuntimeException {
    public PositiveStockException(String message) {
        super(message);
    }
}
