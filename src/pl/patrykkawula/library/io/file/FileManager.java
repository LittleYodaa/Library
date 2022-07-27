package pl.patrykkawula.library.io.file;

import pl.patrykkawula.library.model.Library;

public interface FileManager {
    Library importData();
    void exportData(Library library);
}
