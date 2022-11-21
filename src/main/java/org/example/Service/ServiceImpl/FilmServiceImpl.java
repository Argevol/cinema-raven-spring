package org.example.Service.ServiceImpl;

import org.example.DTO.FilmDTO;
import org.example.Entity.Administrator;
import org.example.Entity.Film;
import org.example.Entity.User;
import org.example.Exception.ServiceException;
import org.example.Mapper.FilmMapper;
import org.example.Repository.AdministratorRepository;
import org.example.Repository.FilmRepository;
import org.example.Repository.UserRepository;
import org.example.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<FilmDTO> getAllFilms(final String role, final String username, final String password) {
        if (!role.equals("USER") && !role.equals("ADMIN")){
            throw new ServiceException(400, "invalid role");
        }

        if (role.toUpperCase().equals("USER")){
            final User user = userRepository.getUserByUsername(username);

            if (!user.getPassword().equals(password)) {
                throw new ServiceException(400, "wrong access key");
            }
        }

        if (role.toUpperCase().equals("ADMIN")){
            final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

            if (!administrator.getPassword().equals(password)) {
                throw new ServiceException(400, "wrong password");
            }
        }

        return filmRepository.getAllFilms().stream()
                .map(FilmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO getFilmById(final String role, final String username, final String password, final Long id) {
        if (!role.equals("USER") && !role.equals("ADMIN")){
            throw new ServiceException(400, "invalid role");
        }

       if (role.toUpperCase().equals("USER")){
           final User user = userRepository.getUserByUsername(username);

           if (!user.getPassword().equals(password)) {
               throw new ServiceException(400, "wrong access key");
           }
       }

       if (role.toUpperCase().equals("ADMIN")){
           final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

           if (!administrator.getPassword().equals(password)) {
               throw new ServiceException(400, "wrong password");
           }
       }

        return FilmMapper.toDTO(filmRepository.getFilmById(id));
    }

    @Override
    public void deleteFilmById(final String username, final String accessKey, final Long id) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        filmRepository.deleteFilmById(id);
    }

    @Override
    public FilmDTO updateAllFilm(final String username, final String accessKey, final FilmDTO filmDTO) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        if (filmDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        return FilmMapper.toDTO(filmRepository.updateAllFilm(FilmMapper.toEntity(filmDTO)));
    }

    @Override
    public FilmDTO saveFilm(final String username, final String accessKey, final FilmDTO filmDTO) {
        final Administrator administrator = administratorRepository.getAdministratorByUsername(username);
        final Film film = FilmMapper.toEntity(filmDTO);

        if (!administrator.getAccessKey().equals(accessKey)) {
            throw new ServiceException(400, "wrong access key");
        }

        if (filmDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (filmRepository.getAllFilms().stream().anyMatch(film::equals)) {
            throw new ServiceException(400, "film is already saved");
        }

        filmRepository.saveFilm(film);
        return FilmMapper.toDTO(film);
    }
}
