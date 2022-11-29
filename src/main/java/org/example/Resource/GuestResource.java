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

    /**
     * Create user and add it to the database
     * @param userDTO user data
     * @return Response code 200
     * @Note To call the method, you need to enter user data
     */
    @PostMapping("/authorize-user")
    public ResponseEntity<UserDTO> authorizeAsUser(@RequestBody @Validated final UserDTO userDTO) {
        return ResponseEntity.ok().body(guestService.authorizeAsUser(userDTO));
    }

    /**
     * Create administrator and add it to the database
     * @param administratorDTO administrator data
     * @return Response code 200
     * @Note To call the method, you need to enter administrator data
     */
    @PostMapping("/authorize-admin")
    public ResponseEntity<AdministratorDTO> authorizeAsAdministrator(@RequestBody @Validated final AdministratorDTO administratorDTO) {
        return ResponseEntity.ok().body(guestService.authorizeAsAdministrator(administratorDTO));
    }
}
