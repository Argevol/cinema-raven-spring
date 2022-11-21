package org.example.Mapper;

import org.example.DTO.MovieSessionDTO;
import org.example.Entity.MovieSession;

public class MovieSessionMapper {
    public static MovieSessionDTO toDTO(final MovieSession movieSession) {
        final MovieSessionDTO movieSessionDTO = new MovieSessionDTO();

        movieSessionDTO.setId(movieSession.getId());
        movieSessionDTO.setFilmId(movieSession.getFilmId());
        movieSessionDTO.setDate(movieSession.getDate());
        movieSessionDTO.setTime(movieSession.getTime());
        movieSessionDTO.setHall(movieSession.getHall());

        return movieSessionDTO;
    }

    public static MovieSession toEntity(final MovieSessionDTO movieSessionDTO) {
        final MovieSession movieSession = new MovieSession();

        movieSession.setId(movieSessionDTO.getId());
        movieSession.setFilmId(movieSessionDTO.getFilmId());
        movieSession.setDate(movieSessionDTO.getDate());
        movieSession.setTime(movieSessionDTO.getTime());
        movieSession.setHall(movieSessionDTO.getHall());

        return movieSession;
    }
}
