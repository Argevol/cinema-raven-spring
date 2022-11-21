package org.example.Service;

import org.example.DTO.MovieSessionDTO;

import java.util.List;

public interface MovieSessionService {
    List<MovieSessionDTO> getAllMovieSessions(String role, String username, String password);
    MovieSessionDTO saveMovieSession(String username, String accessKey, MovieSessionDTO movieSessionDTO);
    MovieSessionDTO getMovieSessionById(String role, String username, String password, Long id);
    MovieSessionDTO updateAllMovieSession(String username, String accessKey, MovieSessionDTO movieSessionDTO);
    void deleteMovieSessionById(String username, String accessKey, Long id);
}
