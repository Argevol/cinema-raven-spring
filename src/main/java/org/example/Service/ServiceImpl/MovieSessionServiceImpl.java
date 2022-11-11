package org.example.Service.ServiceImpl;

import org.example.DTO.MovieSessionDTO;
import org.example.Entity.MovieSession;
import org.example.Exception.ServiceException;
import org.example.Mapper.MovieSessionMapper;
import org.example.Repository.MovieSessionRepository;
import org.example.Service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Autowired
    private MovieSessionRepository movieSessionRepository;

    @Override
    public List<MovieSessionDTO> getAllMovieSessions() {
        return movieSessionRepository.getAllMovieSessions().stream()
                .map(MovieSessionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovieSessionDTO getMovieSessionById(final Long id) {
        return MovieSessionMapper.toDTO(movieSessionRepository.getMovieSessionById(id));
    }

    @Override
    public void deleteMovieSessionById(final Long id) {
        movieSessionRepository.deleteMovieSessionById(id);
    }

    @Override
    public MovieSessionDTO updateAllMovieSession(final MovieSessionDTO movieSessionDTO) {
        if (movieSessionDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        return MovieSessionMapper.toDTO(movieSessionRepository.updateAllMovieSession(MovieSessionMapper.toEntity(movieSessionDTO)));
    }

    @Override
    public MovieSessionDTO saveMovieSession(final MovieSessionDTO movieSessionDTO) {
        final MovieSession movieSession = MovieSessionMapper.toEntity(movieSessionDTO);

        if (movieSessionDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (movieSessionRepository.getAllMovieSessions().stream().anyMatch(MovieSessionMapper.toEntity(movieSessionDTO)::equals)) {
            throw new ServiceException(400, "film is already saved");
        }

        movieSessionRepository.saveMovieSession(movieSession);
        return MovieSessionMapper.toDTO(movieSession);
    }
}
