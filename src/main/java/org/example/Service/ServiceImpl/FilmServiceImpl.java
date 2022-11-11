package org.example.Service.ServiceImpl;

import org.example.DTO.FilmDTO;
import org.example.Entity.Film;
import org.example.Exception.ServiceException;
import org.example.Mapper.FilmMapper;
import org.example.Repository.FilmRepository;
import org.example.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmRepository.getAllFilms().stream()
                .map(FilmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO getFilmById(final Long id) {
        return FilmMapper.toDTO(filmRepository.getFilmById(id));
    }

    @Override
    public void deleteFilmById(final Long id) {
        filmRepository.deleteFilmById(id);
    }

    @Override
    public FilmDTO updateAllFilm(final FilmDTO filmDTO) {
        if (filmDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        return FilmMapper.toDTO(filmRepository.updateAllFilm(FilmMapper.toEntity(filmDTO)));
    }

    @Override
    public FilmDTO saveFilm(final FilmDTO filmDTO) {
        final Film film = FilmMapper.toEntity(filmDTO);

        if (filmDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (filmRepository.getAllFilms().stream().anyMatch(FilmMapper.toEntity(filmDTO)::equals)) {
            throw new ServiceException(400, "film is already saved");
        }

        filmRepository.saveFilm(film);
        return FilmMapper.toDTO(film);
    }
}
