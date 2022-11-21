package org.example.Resource;

import org.example.Service.UserCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {
    @GetMapping("/get")
    public String getCinemaById() {
        return "hello";
    }

    @PostMapping
    public ResponseEntity saveCinema() {
        return ResponseEntity.ok().body("Post method");
    }
}
