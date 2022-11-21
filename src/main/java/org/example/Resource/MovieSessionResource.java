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

    @GetMapping("/{role}/{username}/{password}")
    public ResponseEntity<List<MovieSessionDTO>> getAllMovieSessions(@PathVariable final String role, @PathVariable final String username,
                                                                     @PathVariable final String password) {
        return ResponseEntity.ok().body(movieSessionService.getAllMovieSessions(role, username, password));
    }

    @GetMapping("/{role}/{username}/{password}/{id}")
    public ResponseEntity<MovieSessionDTO> getMovieSessionById(@PathVariable final String role, @PathVariable final String username,
                                                               @PathVariable final String password, @PathVariable final Long id) {
        return ResponseEntity.ok().body(movieSessionService.getMovieSessionById(role, username, password, id));
    }

    @PostMapping("/{username}/{accessKey}")
    public ResponseEntity<MovieSessionDTO> saveMovieSession(@PathVariable final String username, @PathVariable final String accessKey,
                                                            @RequestBody @Validated final MovieSessionDTO movieSessionDTO) {
        return ResponseEntity.ok().body(movieSessionService.saveMovieSession(username, accessKey, movieSessionDTO));
    }

    @PutMapping("/{username}/{accessKey}")
    public ResponseEntity<MovieSessionDTO> updateAllMovieSession(@PathVariable final String username, @PathVariable final String accessKey,
                                                                 @RequestBody @Validated final MovieSessionDTO movieSessionDTO) {
        return ResponseEntity.ok().body(movieSessionService.updateAllMovieSession(username, accessKey, movieSessionDTO));
    }

    @DeleteMapping("/{username}/{accessKey}/{id}")
    public ResponseEntity<?> deleteMovieSessionById(@PathVariable final String username, @PathVariable final String accessKey,
                                                    @PathVariable final Long id) {
        movieSessionService.deleteMovieSessionById(username, accessKey, id);
        return ResponseEntity.ok().build();
    }
}
