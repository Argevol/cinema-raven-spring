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
public class AdministratorDTO {
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String accessKey;
    private Role role;
}
