package org.example.Entity.Enumeration;

public enum Genres {
    ACTION("action"),
    WESTERN("western"),
    MILITARY("military"),
    ADVENTURE("adventure"),
    DETECTIVE("detective"),
    DRAMA("drama"),
    ROMANCE("romance"),
    MELODRAMA("melodrama"),
    COMEDY("comedy"),
    MUSICAL("musical"),
    THRILLER("thriller"),
    HORROR("horror"),
    FAMILY("family"),
    SCIENCE_FICTION("science fiction"),
    FANTASY("fantasy"),
    DOCUMENTARY("documentary"),
    HISTORICAL("historical"),
    ANIMATION("animation");

    private final String genre;

    Genres(final String genre){
        this.genre = genre;
    }

    public String getGenre(){
        return genre;
    }
}
