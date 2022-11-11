package org.example.Mapper;

import org.example.DTO.FilmDTO;
import org.example.Entity.Film;

public class FilmMapper {
    public static FilmDTO toDTO(final Film film) {
        final FilmDTO filmDTO = new FilmDTO();

        filmDTO.setId(film.getId());
        filmDTO.setName(film.getName());
        filmDTO.setReleaseDate(film.getReleaseDate());
        filmDTO.setGenres(film.getGenres());
        filmDTO.setDescription(film.getDescription());
        filmDTO.setDuration(film.getDuration());
        filmDTO.setFilmDetails(film.getFilmDetails());

        return filmDTO;
    }

    public static Film toEntity(final FilmDTO filmDTO) {
        final Film film = new Film();

        film.setId(filmDTO.getId());
        film.setName(filmDTO.getName());
        film.setReleaseDate(filmDTO.getReleaseDate());
        film.setGenres(filmDTO.getGenres());
        film.setDescription(filmDTO.getDescription());
        film.setDuration(filmDTO.getDuration());
        film.setFilmDetails(filmDTO.getFilmDetails());

        return film;
    }
}
