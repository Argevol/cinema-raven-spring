package org.example.Service.ServiceImpl;

import org.example.DTO.CinemaDTO;
import org.example.Entity.Cinema;
import org.example.Exception.ServiceException;
import org.example.Mapper.CinemaMapper;
import org.example.Repository.CinemaRepository;
import org.example.Service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<CinemaDTO> getAllCinemas() {
        return cinemaRepository.getAllCinemas().stream()
                .map(CinemaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CinemaDTO getCinemaById(final Long id) {
        return CinemaMapper.toDTO(cinemaRepository.getCinemaById(id));
    }

    @Override
    public void deleteCinemaById(final Long id) {
        cinemaRepository.deleteCinemaById(id);
    }

    @Override
    public CinemaDTO updateAllCinema(final CinemaDTO cinemaDTO) {
        if (cinemaDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        return CinemaMapper.toDTO(cinemaRepository.updateAllCinema(CinemaMapper.toEntity(cinemaDTO)));
    }

    @Override
    public CinemaDTO saveCinema(final CinemaDTO cinemaDTO) {
        final Cinema cinema = CinemaMapper.toEntity(cinemaDTO);

        if (cinemaDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (cinemaRepository.getAllCinemas().stream().anyMatch(CinemaMapper.toEntity(cinemaDTO)::equals)) {
            throw new ServiceException(400, "film is already saved");
        }

        cinemaRepository.saveCinema(cinema);
        return CinemaMapper.toDTO(cinema);
    }
}
