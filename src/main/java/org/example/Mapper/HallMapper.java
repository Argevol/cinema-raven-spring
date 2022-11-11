package org.example.Mapper;

import org.example.DTO.HallDTO;
import org.example.Entity.Hall;

public class HallMapper {
    public static HallDTO toDTO(final Hall hall) {
        final HallDTO hallDTO = new HallDTO();

        hallDTO.setId(hall.getId());
        hallDTO.setName(hall.getName());
        hallDTO.setFilms(hall.getFilms());

        return hallDTO;
    }

    public static Hall toEntity(final HallDTO hallDTO) {
        final Hall hall = new Hall();

        hall.setId(hallDTO.getId());
        hall.setName(hallDTO.getName());
        hall.setFilms(hallDTO.getFilms());

        return hall;
    }
}
