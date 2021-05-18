package main.part7.entity;

public class Film {
    private int id;
    private String title;
    private int year;
    private Genre genre;

    public enum Genre {
        DRAMA("Drama"),
        MELODRAMA("Melodrama"),
        THRILLER("Thriller"),
        FAMILY_MOVIE("Family_movie"),
        COMEDY("Comedy");
        private String value;

        Genre(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    public Film() {
    }

    public Film(int id, String title, int year, Genre genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0)
            this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genre=" + genre +
                '}';
    }
}
