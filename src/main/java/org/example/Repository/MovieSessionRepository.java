package org.example.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Entity.Film;
import org.example.Entity.MovieSession;
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
public class MovieSessionRepository {
    private List<MovieSession> movieSessions;
    private Long id = 0L;

    @PostConstruct
    public void init() {
        final Path file = Paths.get("movie-sessions.txt");

        try {
            final String savedMovieSessionsAsString = Files.readString(file, StandardCharsets.UTF_16);
            movieSessions = JacksonUtil.deserialize(savedMovieSessionsAsString, new TypeReference<List<MovieSession>>() {
            });

            if (movieSessions == null) {
                movieSessions = new ArrayList<>();
                return;
            }

            final long maxId = movieSessions.stream().mapToLong(MovieSession::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue");
            movieSessions = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("movie-sessions.txt");

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(movieSessions)), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue");
        }
    }


    public List<MovieSession> getAllMovieSessions() {
        return new ArrayList<>(movieSessions);
    }

    public void saveMovieSession(final MovieSession movieSession) {
        movieSession.setId(id);
        ++id;
        movieSessions.add(movieSession);
    }

    public MovieSession getMovieSessionById(final Long id) {
        return movieSessions.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "movie session with id " + id + " not found"));
    }

    public void deleteMovieSessionById(final Long id) {
        movieSessions = movieSessions.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public MovieSession updateAllMovieSession(final MovieSession movieSession) {
        final MovieSession saved = getMovieSessionById(movieSession.getId());

        saved.setFilmId(movieSession.getFilmId());
        saved.setDate(movieSession.getDate());
        saved.setTime(movieSession.getTime());
        saved.setHallId(movieSession.getHallId());

        return saved;
    }
}
