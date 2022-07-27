package pl.patrykkawula.library.exception;

import java.io.IOException;

public class DataImportException extends RuntimeException {
    public DataImportException(String message) {
        super(message);
    }
}
