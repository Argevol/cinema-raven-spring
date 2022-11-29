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

    /**
     * Get all movie sessions from database
     * @param role user or admin role
     * @param username user or admin username
     * @param password user or admin password
     * @return List of MovieSessionDTO and response code 200
     * @Note To call the method, you need to enter user or administrator data in the path
     */
    @GetMapping("/{role}/{username}/{password}")
    public ResponseEntity<List<MovieSessionDTO>> getAllMovieSessions(@PathVariable final String role, @PathVariable final String username,
                                                                     @PathVariable final String password) {
        return ResponseEntity.ok().body(movieSessionService.getAllMovieSessions(role, username, password));
    }

    /**
     * Get movie session by id from database
     * @param role user or admin role
     * @param username user or admin username
     * @param password user or admin password
     * @param id movie session id
     * @return MovieSessionDTO and response code 200
     * @Note To call the method, you need to enter user or administrator data in the path.
     * Enter id to try to find session by id in database
     */
    @GetMapping("/{role}/{username}/{password}/{id}")
    public ResponseEntity<MovieSessionDTO> getMovieSessionById(@PathVariable final String role, @PathVariable final String username,
                                                               @PathVariable final String password, @PathVariable final Long id) {
        return ResponseEntity.ok().body(movieSessionService.getMovieSessionById(role, username, password, id));
    }

    /**
     * Create movie session and add it to the database
     * @param username admin username
     * @param accessKey admin access key
     * @return Response code 200
     * @Note To call the method, you need to enter administrator data in the path
     */
    @PostMapping("/{username}/{accessKey}")
    public ResponseEntity<MovieSessionDTO> saveMovieSession(@PathVariable final String username, @PathVariable final String accessKey,
                                                            @RequestBody @Validated final MovieSessionDTO movieSessionDTO) {
        return ResponseEntity.ok().body(movieSessionService.saveMovieSession(username, accessKey, movieSessionDTO));
    }

    /**
     * Overwrites all movie session data and add it to the database
     * @param username admin username
     * @param accessKey admin access key
     * @return Response code 200
     * @Note To call the method, you need to enter administrator data in the path
     */
    @PutMapping("/{username}/{accessKey}")
    public ResponseEntity<MovieSessionDTO> updateAllMovieSession(@PathVariable final String username, @PathVariable final String accessKey,
                                                                 @RequestBody @Validated final MovieSessionDTO movieSessionDTO) {
        return ResponseEntity.ok().body(movieSessionService.updateAllMovieSession(username, accessKey, movieSessionDTO));
    }

    /**
     * Delete movie session from the database by id
     * @param username admin username
     * @param accessKey admin access key
     * @param id movie session id
     * @return Response code 200
     * @Note To call the method, you need to enter administrator data in the path
     */
    @DeleteMapping("/{username}/{accessKey}/{id}")
    public ResponseEntity<?> deleteMovieSessionById(@PathVariable final String username, @PathVariable final String accessKey,
                                                    @PathVariable final Long id) {
        movieSessionService.deleteMovieSessionById(username, accessKey, id);
        return ResponseEntity.ok().build();
    }
}
