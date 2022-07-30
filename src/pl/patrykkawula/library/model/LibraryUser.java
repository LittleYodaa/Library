package pl.patrykkawula.library.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryUser extends User {

    List<Publication> publicationHistory = new ArrayList<>();
    List<Publication> borrowedPublication = new ArrayList<>();

    public List<Publication> getPublicationHistory() {
        return publicationHistory;
    }

    public List<Publication> getBorrowedPublication() {
        return borrowedPublication;
    }

    public LibraryUser(String firstName, String lastName, String pesel) {
        super(firstName, lastName, pesel);
    }

    private void addPublicationToHistory(Publication pub) {
        publicationHistory.add(pub);
    }

    public void borrowPublication (Publication pub) {
        borrowedPublication.add(pub);
    }

    public boolean returnPublication (Publication pub) {
        boolean result = false;
        if (borrowedPublication.remove(pub)) {
            result = true;
            addPublicationToHistory(pub);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryUser)) return false;
        if (!super.equals(o)) return false;

        LibraryUser that = (LibraryUser) o;

        if (publicationHistory != null ? !publicationHistory.equals(that.publicationHistory) : that.publicationHistory != null)
            return false;
        return borrowedPublication != null ? borrowedPublication.equals(that.borrowedPublication) : that.borrowedPublication == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (publicationHistory != null ? publicationHistory.hashCode() : 0);
        result = 31 * result + (borrowedPublication != null ? borrowedPublication.hashCode() : 0);
        return result;
    }

    @Override
    public String toCsv() {
        return getFirstName() + ";" + getLastName() + ";" + getPesel();
    }


}
