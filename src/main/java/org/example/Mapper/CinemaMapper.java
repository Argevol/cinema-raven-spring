package org.example.Mapper;

import org.example.DTO.CinemaDTO;
import org.example.Entity.Cinema;

public class CinemaMapper {
    public static CinemaDTO toDTO(final Cinema cinema) {
        final CinemaDTO cinemaDTO = new CinemaDTO();

        cinemaDTO.setId(cinema.getId());
        cinemaDTO.setName(cinema.getName());
        cinemaDTO.setMapOfAllFilmSessions(cinema.getMapOfAllFilmSessions());

        return cinemaDTO;
    }

    public static Cinema toEntity(final CinemaDTO cinemaDTO) {
        final Cinema cinema = new Cinema();

        cinema.setId(cinemaDTO.getId());
        cinema.setName(cinemaDTO.getName());
        cinema.setMapOfAllFilmSessions(cinemaDTO.getMapOfAllFilmSessions());

        return cinema;
    }
}
