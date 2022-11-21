package org.example.Service;

import org.example.DTO.CinemaDTO;

import java.util.List;

public interface CinemaService {
    List<CinemaDTO> getAllCinemas(String role, String username, String password);
    CinemaDTO saveCinema(String username, String accessKey, CinemaDTO cinemaDTO);
    CinemaDTO getCinemaById(String role, String username, String password, Long id);
    CinemaDTO updateAllCinema(String username, String accessKey, CinemaDTO cinemaDTO);
    void deleteCinemaById(String username, String accessKey, Long id);
}
