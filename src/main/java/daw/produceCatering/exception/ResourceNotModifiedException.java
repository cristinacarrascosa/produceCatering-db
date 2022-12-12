package daw.produceCatering.exception;

public class ResourceNotModifiedException extends RuntimeException{

    public ResourceNotModifiedException(String message) {
        super("ERROR: Resource not modified. " + message);
    }
    
}
