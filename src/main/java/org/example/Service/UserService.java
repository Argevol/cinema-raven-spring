package org.example.Service;

import org.example.DTO.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO saveUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO updateAllUser(UserDTO userDTO);
    void deleteUserById(Long id);
}
