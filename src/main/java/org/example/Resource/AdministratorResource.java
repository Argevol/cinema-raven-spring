package org.example.Resource;

import org.example.DTO.CinemaDTO;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1/admin")
public class AdministratorResource {
    @GetMapping("/get")
    public String getCinemaById() {
        return "hello";
    }

    @PostMapping
    public ResponseEntity saveCinema() {
        return ResponseEntity.ok().body("Post method");
    }
}
