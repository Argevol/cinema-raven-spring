package org.example.Resource;

import org.example.DTO.CinemaDTO;
import org.example.Service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cinema")
public class CinemaResource {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/{role}/{username}/{password}")
    public ResponseEntity<List<CinemaDTO>> getAllMovieSessions(@PathVariable final String role, @PathVariable final String username,
                                                               @PathVariable final String password) {
        return ResponseEntity.ok().body(cinemaService.getAllCinemas(role, username, password));
    }

    @GetMapping("/{role}/{username}/{password}/{id}")
    public ResponseEntity<CinemaDTO> getCinemaById(@PathVariable final String role, @PathVariable final String username,
                                                   @PathVariable final String password, @PathVariable final Long id) {
        return ResponseEntity.ok().body(cinemaService.getCinemaById(role, username, password, id));
    }

    @PostMapping("/{username}/{accessKey}")
    public ResponseEntity<CinemaDTO> saveCinema(@PathVariable final String username, @PathVariable final String accessKey,
                                                @RequestBody @Validated final CinemaDTO cinemaDTO) {
        return ResponseEntity.ok().body(cinemaService.saveCinema(username, accessKey, cinemaDTO));
    }

    @PutMapping("/{username}/{accessKey}")
    public ResponseEntity<CinemaDTO> updateAllCinema(@PathVariable final String username, @PathVariable final String accessKey,
                                                     @RequestBody @Validated final CinemaDTO cinemaDTO) {
        return ResponseEntity.ok().body(cinemaService.updateAllCinema(username, accessKey, cinemaDTO));
    }

    @DeleteMapping("/{username}/{accessKey}/{id}")
    public ResponseEntity<?> deleteCinemaById(@PathVariable final String username, @PathVariable final String accessKey,
                                              @PathVariable final Long id) {
        cinemaService.deleteCinemaById(username, accessKey, id);
        return ResponseEntity.ok().build();
    }
}
