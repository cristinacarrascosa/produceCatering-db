package daw.produceCatering.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super("Error: Resource not found. " + message);
    }
    
}
