package daw.produceCatering.exception;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super("ERROR: Los datos del campo no son válidos: " + message);
    }
    
}
