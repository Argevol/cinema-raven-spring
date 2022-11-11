package org.example.Service;

import org.example.DTO.MovieSessionDTO;

import java.util.List;

public interface MovieSessionService {
    List<MovieSessionDTO> getAllMovieSessions();
    MovieSessionDTO saveMovieSession(MovieSessionDTO movieSessionDTO);
    MovieSessionDTO getMovieSessionById(Long id);
    MovieSessionDTO updateAllMovieSession(MovieSessionDTO movieSessionDTO);
    void deleteMovieSessionById(Long id);
}
