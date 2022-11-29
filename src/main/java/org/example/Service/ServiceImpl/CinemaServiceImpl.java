package org.example.Service.ServiceImpl;

import org.example.DTO.CinemaDTO;
import org.example.Entity.Administrator;
import org.example.Entity.Cinema;
import org.example.Entity.MovieSession;
import org.example.Entity.User;
import org.example.Exception.ServiceException;
import org.example.Mapper.CinemaMapper;
import org.example.Repository.*;
import org.example.Service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private MovieSessionRepository movieSessionRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     */
    @Override
    public List<CinemaDTO> getAllCinemas(final String role, final String username, final String password) {
        if (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN")){
            throw new ServiceException(400, "invalid role");
        }

        if (role.equalsIgnoreCase("USER")){
            final User user = userRepository.getUserByUsername(username);

            if (!user.getPassword().equals(password)) {
                throw new ServiceException(400, "wrong access key");
            }
        }

        if (role.equalsIgnoreCase("ADMIN")){
            final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

            if (!administrator.getPassword().equals(password)) {
                throw new ServiceException(400, "wrong password");
            }
        }

        return cinemaRepository.getAllCinemas().stream()
                .map(CinemaMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     */
    @Override
    public CinemaDTO getCinemaById(final String role, final String username, final String password, final Long id) {
        if (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN")){
            throw new ServiceException(400, "invalid role");
        }

        if (role.equalsIgnoreCase("USER")){
            final User user = userRepository.getUserByUsername(username);

            if (!user.getPassword().equals(password)) {
                throw new ServiceException(400, "wrong access key");
            }
        }

        if (role.equalsIgnoreCase("ADMIN")){
            final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

            if (!administrator.getPassword().equals(password)) {
                throw new ServiceException(400, "wrong password");
            }
        }

        return CinemaMapper.toDTO(cinemaRepository.getCinemaById(id));
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     */
    @Override
    public void deleteCinemaById(final String username, final String accessKey, final Long id) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        cinemaRepository.deleteCinemaById(id);
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     * and bad requests
     */
    @Override
    public CinemaDTO updateAllCinema(final String username, final String accessKey, final CinemaDTO cinemaDTO) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        if (cinemaDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        return CinemaMapper.toDTO(cinemaRepository.updateAllCinema(CinemaMapper.toEntity(cinemaDTO)));
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     * and bad requests
     */
    @Override
    public CinemaDTO saveCinema(final String username, final String accessKey, final CinemaDTO cinemaDTO) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        final Cinema cinema = CinemaMapper.toEntity(cinemaDTO);
        final HashMap<Long, List<Long>> sessions = cinemaDTO.getMapOfAllFilmSessions();
        MovieSession movieSession;

        if (cinemaDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        for (final Long filmId : sessions.keySet()) {
            filmRepository.getFilmById(filmId);
            for (final Long movieSessionsId : sessions.get(filmId)) {
                movieSession = movieSessionRepository.getMovieSessionById(movieSessionsId);

                if (!movieSession.getFilmId().equals(filmId)) {
                    throw new ServiceException(400, "One of the lists contains inappropriate movie session");
                }
            }
        }

        if (cinemaRepository.getAllCinemas().stream().anyMatch(cinema::equals)) {
            throw new ServiceException(400, "cinema is already saved");
        }

        cinemaRepository.saveCinema(cinema);
        return CinemaMapper.toDTO(cinema);
    }
}
