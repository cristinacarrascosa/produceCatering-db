package daw.produceCatering.exception;

public class CannotPerformOperationException extends RuntimeException {

    public CannotPerformOperationException(String msg) {
        super("ERROR: No se puede realizar la operacion: " + msg);
    }
    
}
