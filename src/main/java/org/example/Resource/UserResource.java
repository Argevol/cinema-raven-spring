package org.example.Resource;

import org.example.DTO.UserDTO;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hall")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Validated final UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.saveUser(userDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateAllUser(@RequestBody @Validated final UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.updateAllUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable final Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
