package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.Entity.Enumeration.Role;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private Role role;
}
