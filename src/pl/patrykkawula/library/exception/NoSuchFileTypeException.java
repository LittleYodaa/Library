package pl.patrykkawula.library.exception;

import java.nio.file.NoSuchFileException;

public class NoSuchFileTypeException extends RuntimeException {
    public NoSuchFileTypeException(String message) {
        super(message);
    }
}
