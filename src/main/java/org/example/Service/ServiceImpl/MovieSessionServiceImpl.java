package org.example.Service.ServiceImpl;

import org.example.DTO.MovieSessionDTO;
import org.example.Entity.Administrator;
import org.example.Entity.MovieSession;
import org.example.Entity.User;
import org.example.Exception.ServiceException;
import org.example.Mapper.MovieSessionMapper;
import org.example.Repository.AdministratorRepository;
import org.example.Repository.FilmRepository;
import org.example.Repository.MovieSessionRepository;
import org.example.Repository.UserRepository;
import org.example.Service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
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
    public List<MovieSessionDTO> getAllMovieSessions(final String role, final String username, final String password) {
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

        return movieSessionRepository.getAllMovieSessions().stream()
                .map(MovieSessionMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     */
    @Override
    public MovieSessionDTO getMovieSessionById(final String role, final String username, final String password, final Long id) {
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

        return MovieSessionMapper.toDTO(movieSessionRepository.getMovieSessionById(id));
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     */
    @Override
    public void deleteMovieSessionById(final String username, final String accessKey, final Long id) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        movieSessionRepository.deleteMovieSessionById(id);
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     * and bad requests
     */
    @Override
    public MovieSessionDTO updateAllMovieSession(final String username, final String accessKey, final MovieSessionDTO movieSessionDTO) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        if (movieSessionDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        filmRepository.getFilmById(movieSessionDTO.getFilmId());

        return MovieSessionMapper.toDTO(movieSessionRepository.updateAllMovieSession(MovieSessionMapper.toEntity(movieSessionDTO)));
    }

    /**
     * @throws ServiceException request Exception
     * @Note The method checks for exceptions related to access rights
     * and bad requests
     */
    @Override
    public MovieSessionDTO saveMovieSession(final String username, final String accessKey, final MovieSessionDTO movieSessionDTO) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);
        final MovieSession movieSession = MovieSessionMapper.toEntity(movieSessionDTO);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        if (movieSessionDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        filmRepository.getFilmById(movieSession.getFilmId());

        if (movieSessionRepository.getAllMovieSessions().stream().anyMatch(movieSession::equals)) {
            throw new ServiceException(400, "movie session is already saved");
        }

        movieSessionRepository.saveMovieSession(movieSession);
        return MovieSessionMapper.toDTO(movieSession);
    }
}
