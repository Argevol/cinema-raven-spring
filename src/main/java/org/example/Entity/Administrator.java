package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.Entity.Enumeration.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String username;
    private String password;
    private String accessKey;
    private Role role;
}
