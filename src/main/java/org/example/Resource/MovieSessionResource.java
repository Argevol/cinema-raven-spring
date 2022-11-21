package org.example.Resource;

import org.example.DTO.MovieSessionDTO;
import org.example.Service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie-session")
public class MovieSessionResource {
    @Autowired
    private MovieSessionService movieSessionService;

    @GetMapping
    public ResponseEntity<List<MovieSessionDTO>> getAllMovieSessions() {
        return ResponseEntity.ok().body(movieSessionService.getAllMovieSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieSessionDTO> getMovieSessionById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(movieSessionService.getMovieSessionById(id));
    }

    @PostMapping
    public ResponseEntity<MovieSessionDTO> saveMovieSession(@RequestBody @Validated final MovieSessionDTO movieSessionDTO) {
        return ResponseEntity.ok().body(movieSessionService.saveMovieSession(movieSessionDTO));
    }

    @PutMapping
    public ResponseEntity<MovieSessionDTO> updateAllMovieSession(@RequestBody @Validated final MovieSessionDTO movieSessionDTO) {
        return ResponseEntity.ok().body(movieSessionService.updateAllMovieSession(movieSessionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieSessionById(@PathVariable final Long id) {
        movieSessionService.deleteMovieSessionById(id);
        return ResponseEntity.ok().build();
    }
}
