package org.example.Mapper;

import org.example.DTO.AdministratorDTO;
import org.example.Entity.Administrator;
import org.example.Entity.Enumeration.Role;

public class AdministratorMapper {
    public static AdministratorDTO toDTO(final Administrator administrator) {
        final AdministratorDTO administratorDTO = new AdministratorDTO();

        administratorDTO.setId(administrator.getId());
        administratorDTO.setUsername(administrator.getUsername());
        administratorDTO.setPassword(administrator.getPassword());
        administratorDTO.setAccessKey(administrator.getAccessKey());

        return administratorDTO;
    }

    public static Administrator toEntity(final AdministratorDTO administratorDTO) {
        final Administrator administrator = new Administrator();

        administrator.setId(administratorDTO.getId());
        administrator.setUsername(administratorDTO.getUsername());
        administrator.setPassword(administratorDTO.getPassword());
        administrator.setAccessKey(administratorDTO.getAccessKey());
        administrator.setRole(Role.ADMIN);

        return administrator;
    }
}
