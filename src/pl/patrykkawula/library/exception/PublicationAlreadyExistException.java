package pl.patrykkawula.library.exception;

public class PublicationAlreadyExistException extends RuntimeException {
    public PublicationAlreadyExistException(String message) {
        super(message);
    }
}
