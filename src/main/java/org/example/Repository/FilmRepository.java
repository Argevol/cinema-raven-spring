package org.example.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Entity.Film;
import org.example.Exception.ServiceException;
import org.example.Utils.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class FilmRepository {
    private List<Film> films;
    private Long id = 0L;

    @PostConstruct
    public void init() {
        final Path file = Paths.get("films.txt");

        try {
            final String savedFilmsAsString = Files.readString(file, StandardCharsets.UTF_16);
            films = JacksonUtil.deserialize(savedFilmsAsString, new TypeReference<List<Film>>() {
            });

            if (films == null) {
                films = new ArrayList<>();
                return;
            }

            final long maxId = films.stream().mapToLong(Film::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue");
            films = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("films.txt");

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(films)), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue");
        }
    }

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
