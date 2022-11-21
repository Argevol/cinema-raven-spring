package org.example.Service;

import org.example.DTO.AdministratorDTO;
import org.example.DTO.UserDTO;

public interface GuestService {
    UserDTO authorizeAsUser(UserDTO userDTO);
    AdministratorDTO authorizeAsAdministrator(AdministratorDTO administratorDTO);
}
