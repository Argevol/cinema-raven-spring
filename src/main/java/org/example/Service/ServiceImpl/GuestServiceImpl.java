package org.example.Service.ServiceImpl;

import org.example.DTO.AdministratorDTO;
import org.example.DTO.UserDTO;
import org.example.Entity.Administrator;
import org.example.Entity.User;
import org.example.Exception.ServiceException;
import org.example.Mapper.AdministratorMapper;
import org.example.Mapper.UserMapper;
import org.example.Repository.AdministratorRepository;
import org.example.Repository.UserRepository;
import org.example.Service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public UserDTO authorizeAsUser(final UserDTO userDTO) {
        final User user = UserMapper.toEntity(userDTO);

        if (userDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (userRepository.getAllUsers().stream().anyMatch(user::equals)) {
            throw new ServiceException(400, "users is already saved");
        }

        userRepository.saveUser(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public AdministratorDTO authorizeAsAdministrator(final AdministratorDTO administratorDTO) {
        final Administrator administrator = AdministratorMapper.toEntity(administratorDTO);

        if (administratorDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (administratorRepository.getAllAdministrators().stream().anyMatch(administrator::equals)) {
            throw new ServiceException(400, "administrator is already saved");
        }

        administratorRepository.saveAdministrator(administrator);
        return AdministratorMapper.toDTO(administrator);
    }
}
