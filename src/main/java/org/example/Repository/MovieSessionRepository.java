package org.example.Repository;

import org.example.Entity.MovieSession;
import org.example.Exception.ServiceException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieSessionRepository {
    private List<MovieSession> movieSessions;
    private Long id = 0L;

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
