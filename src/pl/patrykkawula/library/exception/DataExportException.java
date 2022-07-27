package pl.patrykkawula.library.exception;

import java.io.IOException;

public class DataExportException extends RuntimeException {
    public DataExportException(String message) {
       super(message);
    }
}
