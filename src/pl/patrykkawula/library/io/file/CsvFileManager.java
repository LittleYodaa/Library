package pl.patrykkawula.library.io.file;

import pl.patrykkawula.library.exception.DataExportException;
import pl.patrykkawula.library.exception.DataImportException;
import pl.patrykkawula.library.exception.InvalidDataException;
import pl.patrykkawula.library.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";
    private static final String USER_FILE_NAME = "Library_users.csv";

    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
    }

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    private Publication createObjectFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        }
        throw new InvalidDataException("Nieznany typ publikacji " + type);
    }

    private Book createBook(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        String author = data[4];
        int pages = Integer.valueOf(data[5]);
        String isbn = data[6];
        return new Book(title, author, year, pages, publisher, isbn);
    }

    private Magazine createMagazine(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        int month = Integer.valueOf(data[4]);
        int day = Integer.valueOf(data[5]);
        String language = data[6];
        return new Magazine(title, publisher, language, year, month, day);
    }

    private void importPublications(Library library) {
        try (BufferedReader bufferedReader =new BufferedReader(new FileReader(FILE_NAME))) {
//            while (fileReader.hasNextLine()) {
//                String line = fileReader.nextLine();
//                Publication publication = createObjectFromString(line);
//                library.addPublication(publication);
                bufferedReader.lines()
                        .map(this::createObjectFromString)
                        .forEach(library::addPublication);
        } catch (IOException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        }
    }

    private void importUsers(Library library) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USER_FILE_NAME))) {
//            while (fileReader.hasNextLine()) {
//                String line = fileReader.nextLine();
//                LibraryUser libUser = createUserFromString(line);
//                library.addUser(libUser);
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(library::addUser);
        } catch (IOException e) {
            throw new DataImportException("Brak pliku " + USER_FILE_NAME);
        }
    }

    private LibraryUser createUserFromString(String line) {
        String[] split = line.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        return new LibraryUser(firstName, lastName, pesel);
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications, FILE_NAME);
    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCsv(users, USER_FILE_NAME);
    }

    private <T extends csvConvertible> void exportToCsv(Collection<T> collection, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (T element : collection) {
                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + fileName);
        }
    }
}






