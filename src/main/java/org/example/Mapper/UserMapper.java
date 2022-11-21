package org.example.Mapper;

import org.example.DTO.UserDTO;
import org.example.Entity.Enumeration.Role;
import org.example.Entity.User;

public class UserMapper {
    public static UserDTO toDTO(final User user) {
        final UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        user.setRole(Role.USER);

        return userDTO;
    }

    public static User toEntity(final UserDTO userDTO) {
        final User user = new User();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.USER);

        return user;
    }
}
