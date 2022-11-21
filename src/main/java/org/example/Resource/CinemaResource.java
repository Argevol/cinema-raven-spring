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

    @GetMapping
    public ResponseEntity<List<CinemaDTO>> getAllMovieSessions() {
        return ResponseEntity.ok().body(cinemaService.getAllCinemas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CinemaDTO> getCinemaById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(cinemaService.getCinemaById(id));
    }

    @PostMapping
    public ResponseEntity<CinemaDTO> saveCinema(@RequestBody @Validated final CinemaDTO cinemaDTO) {
        return ResponseEntity.ok().body(cinemaService.saveCinema(cinemaDTO));
    }

    @PutMapping
    public ResponseEntity<CinemaDTO> updateAllCinema(@RequestBody @Validated final CinemaDTO cinemaDTO) {
        return ResponseEntity.ok().body(cinemaService.updateAllCinema(cinemaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCinemaById(@PathVariable final Long id) {
        cinemaService.deleteCinemaById(id);
        return ResponseEntity.ok().build();

    }
}
