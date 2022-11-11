package org.example.Service;

import org.example.DTO.HallDTO;

import java.util.List;

public interface HallService {
    List<HallDTO> getAllHalls();
    HallDTO saveHall(HallDTO hallDTO);
    HallDTO getHallById(Long id);
    HallDTO updateAllHall(HallDTO hallDTO);
    void deleteHallById(Long id);
}
