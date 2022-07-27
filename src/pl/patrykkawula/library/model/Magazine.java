package pl.patrykkawula.library.model;

public class Magazine extends Publication {
    public static final String TYPE = "Magazyn";
    private int month;
    private int day;
    private String language;

    public Magazine(String title, String publisher, String language, int year, int month, int day) {
        super(title, publisher, year);
        this.language = language;
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return super.toString() + month + ", " + day + ", " + language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine)) return false;

        Magazine magazine = (Magazine) o;

        if (month != magazine.month) return false;
        if (day != magazine.day) return false;
        return language != null ? language.equals(magazine.language) : magazine.language == null;
    }

    @Override
    public int hashCode() {
        int result = month;
        result = 31 * result + day;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() + ";" +
                getPublisher() + ";" +
                getYear() + ";" +
                month + ";" +
                day + ";" +
                language + "";
    }
}
