package org.example.Service.ServiceImpl;

import org.example.DTO.UserDTO;
import org.example.Entity.User;
import org.example.Exception.ServiceException;
import org.example.Mapper.UserMapper;
import org.example.Repository.UserRepository;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(final Long id) {
        return UserMapper.toDTO(userRepository.getUserById(id));
    }

    @Override
    public void deleteUserById(final Long id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public UserDTO updateAllUser(final UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new ServiceException(400, "id is null");
        }

        return UserMapper.toDTO(userRepository.updateAllUser(UserMapper.toEntity(userDTO)));
    }

    @Override
    public UserDTO saveUser(final UserDTO userDTO) {
        final User user = UserMapper.toEntity(userDTO);

        if (userDTO.getId() != null) {
            throw new ServiceException(400, "id not null");
        }

        if (userRepository.getAllUsers().stream().anyMatch(UserMapper.toEntity(userDTO)::equals)) {
            throw new ServiceException(400, "film is already saved");
        }

        userRepository.saveUser(user);
        return UserMapper.toDTO(user);
    }
}
