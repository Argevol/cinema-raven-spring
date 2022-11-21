package org.example.Service;

import org.example.DTO.FilmDTO;

import java.util.List;

public interface FilmService {
    List<FilmDTO> getAllFilms(String role, String username, String password);
    FilmDTO saveFilm(String username, String accessKey, FilmDTO filmDTO);
    FilmDTO getFilmById(String role, String username, String password, Long id);
    FilmDTO updateAllFilm(String username, String accessKey, FilmDTO filmDTO);
    void deleteFilmById(String username, String accessKey, Long id);
}
