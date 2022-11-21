package org.example.Service;

import org.example.DTO.FilmDTO;

import java.util.List;

public interface FilmService {
    List<FilmDTO> getAllFilms();
    FilmDTO saveFilm(FilmDTO filmDTO);
    FilmDTO getFilmById(Long id);
    FilmDTO updateAllFilm(FilmDTO filmDTO);
    void deleteFilmById(Long id);
}
