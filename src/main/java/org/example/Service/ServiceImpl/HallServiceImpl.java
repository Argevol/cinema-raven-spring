package org.example.Service.ServiceImpl;

import org.example.DTO.HallDTO;
import org.example.Entity.Hall;
import org.example.Exception.ServiceException;
import org.example.Mapper.HallMapper;
import org.example.Repository.HallRepository;
import org.example.Service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;

    @Override
    public List<HallDTO> getAllHalls() {
        return hallRepository.getAllHalls().stream()
                .map(HallMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HallDTO getHallById(final Long id) {
        return HallMapper.toDTO(hallRepository.getHallById(id));
    }

    @Override
    public void deleteHallById(final Long id) {
        hallRepository.deleteHallById(id);
    }

    @Override
    public HallDTO updateAllHall(final HallDTO hallDTO) {
        if (hallDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        return HallMapper.toDTO(hallRepository.updateAllHall(HallMapper.toEntity(hallDTO)));
    }

    @Override
    public HallDTO saveHall(final HallDTO hallDTO) {
        final Hall hall = HallMapper.toEntity(hallDTO);

        if (hallDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (hallRepository.getAllHalls().stream().anyMatch(HallMapper.toEntity(hallDTO)::equals)) {
            throw new ServiceException(400, "hall is already saved");
        }

        hallRepository.saveHall(hall);
        return HallMapper.toDTO(hall);
    }
}
