package org.example.Service;

import org.example.DTO.CinemaDTO;

import java.util.List;

public interface CinemaService {
    List<CinemaDTO> getAllCinemas();
    CinemaDTO saveCinema(CinemaDTO cinemaDTO);
    CinemaDTO getCinemaById(Long id);
    CinemaDTO updateAllCinema(CinemaDTO cinemaDTO);
    void deleteCinemaById(Long id);
}
