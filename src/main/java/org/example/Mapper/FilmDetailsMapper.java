package org.example.Mapper;

import org.example.DTO.FilmDetailsDTO;
import org.example.Entity.FilmDetails;

public class FilmDetailsMapper {
    public static FilmDetailsDTO toDTO(final FilmDetails filmDetails) {
        final FilmDetailsDTO filmDetailsDTO = new FilmDetailsDTO();

        filmDetailsDTO.setCountry(filmDetails.getCountry());
        filmDetailsDTO.setAgeRestriction(filmDetails.getAgeRestriction());
        filmDetailsDTO.setDirectors(filmDetails.getDirectors());
        filmDetailsDTO.setActors(filmDetails.getActors());

        return filmDetailsDTO;
    }

    public static FilmDetails toEntity(final FilmDetailsDTO filmDetailsDTO) {
        final FilmDetails filmDetails = new FilmDetails();

        filmDetails.setCountry(filmDetailsDTO.getCountry());
        filmDetails.setAgeRestriction(filmDetailsDTO.getAgeRestriction());
        filmDetails.setDirectors(filmDetailsDTO.getDirectors());
        filmDetails.setActors(filmDetailsDTO.getActors());

        return filmDetails;
    }
}
