package org.example.Resource;

import org.example.DTO.AdministratorDTO;
import org.example.DTO.UserDTO;
import org.example.Service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guest")
public class GuestResource {
    @Autowired
    private GuestService guestService;

    @PostMapping("/authorize-user")
    public ResponseEntity<UserDTO> authorizeAsUser(@RequestBody @Validated final UserDTO userDTO) {
        return ResponseEntity.ok().body(guestService.authorizeAsUser(userDTO));
    }

    @PostMapping("/authorize-admin")
    public ResponseEntity<AdministratorDTO> authorizeAsAdministrator(@RequestBody @Validated final AdministratorDTO administratorDTO) {
        return ResponseEntity.ok().body(guestService.authorizeAsAdministrator(administratorDTO));
    }
}
