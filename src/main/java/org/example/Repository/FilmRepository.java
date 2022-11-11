package org.example.Repository;

import org.example.Entity.Film;
import org.example.Exception.ServiceException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FilmRepository {
    private List<Film> films;
    private Long id = 0L;

    public List<Film> getAllFilms() {
        return new ArrayList<>(films);
    }

    public void saveFilm(final Film film) {
        film.setId(id);
        ++id;
        films.add(film);
    }

    public Film getFilmById(final Long id) {
        return films.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "film with id " + id + " not found"));
    }

    public void deleteFilmById(final Long id) {
        films = films.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Film updateAllFilm(final Film film) {
        final Film saved = getFilmById(film.getId());

        saved.setName(film.getName());
        saved.setReleaseDate(film.getReleaseDate());
        saved.setDescription(film.getDescription());
        saved.setDuration(film.getDuration());
        saved.setFilmDetails(film.getFilmDetails());

        return saved;
    }
}
