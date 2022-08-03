package pl.patrykkawula.library.model;

import pl.patrykkawula.library.exception.PublicationAlreadyExistException;
import pl.patrykkawula.library.exception.UserAlreadyExistException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Optional<Publication> findPublicationByTitle (String title) {
        return Optional.ofNullable(publications.get(title));
    }

    public Collection<Publication> getSortedPublilcations(Comparator<Publication> comparator) {
        List<Publication> list = new ArrayList<>(publications.values());
        list.sort(comparator);
        return list;
    }

    public Collection<LibraryUser> getSortedUsers (Comparator<LibraryUser> comparator) {
        List<LibraryUser> list = new ArrayList<>(users.values());
        list.sort(comparator);
        return list;
    }

    public void addUser(LibraryUser user) {
        if (users.containsKey(user.getPesel()))
            throw new UserAlreadyExistException("Użytkownik ze wskazanym peselem już istnieje " + user.getPesel());
        users.put(user.getPesel(), user);
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTitle()))
            throw new PublicationAlreadyExistException
                    ("Publikacja o takim tytule już istnieje " + publication.getTitle());
        publications.put(publication.getTitle(), publication);
    }

    public boolean removePublications(Publication publication) {
        if (publications.containsValue(publication)) {
            publications.remove(publication.getTitle());
            return true;
        } else {
            return false;
        }
    }
}

