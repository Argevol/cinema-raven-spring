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

    /**
     * Get all cinemas from database
     * @param role user or admin role
     * @param username user or admin username
     * @param password user or admin password
     * @return List of CinemaDTO and response code 200
     * @Note To call the method, you need to enter user or administrator data in the path
     */
    @GetMapping("/{role}/{username}/{password}")
    public ResponseEntity<List<CinemaDTO>> getAllMovieSessions(@PathVariable final String role, @PathVariable final String username,
                                                               @PathVariable final String password) {
        return ResponseEntity.ok().body(cinemaService.getAllCinemas(role, username, password));
    }

    /**
     * Get cinema by id from database
     * @param role user or admin role
     * @param username user or admin username
     * @param password user or admin password
     * @param id cinema id
     * @return CinemaDTO and response code 200
     * @Note To call the method, you need to enter user or administrator data in the path.
     * Enter id to try to find cinema by id in database
     */
    @GetMapping("/{role}/{username}/{password}/{id}")
    public ResponseEntity<CinemaDTO> getCinemaById(@PathVariable final String role, @PathVariable final String username,
                                                   @PathVariable final String password, @PathVariable final Long id) {
        return ResponseEntity.ok().body(cinemaService.getCinemaById(role, username, password, id));
    }

    /**
     * Create cinema and add it to the database
     * @param username admin username
     * @param accessKey admin access key
     * @return Response code 200
     * @Note To call the method, you need to enter administrator data in the path
     */
    @PostMapping("/{username}/{accessKey}")
    public ResponseEntity<CinemaDTO> saveCinema(@PathVariable final String username, @PathVariable final String accessKey,
                                                @RequestBody @Validated final CinemaDTO cinemaDTO) {
        return ResponseEntity.ok().body(cinemaService.saveCinema(username, accessKey, cinemaDTO));
    }

    /**
     * Overwrites all cinema data and add it to the database
     * @param username admin username
     * @param accessKey admin access key
     * @return Response code 200
     * @Note To call the method, you need to enter administrator data in the path
     */
    @PutMapping("/{username}/{accessKey}")
    public ResponseEntity<CinemaDTO> updateAllCinema(@PathVariable final String username, @PathVariable final String accessKey,
                                                     @RequestBody @Validated final CinemaDTO cinemaDTO) {
        return ResponseEntity.ok().body(cinemaService.updateAllCinema(username, accessKey, cinemaDTO));
    }

    /**
     * Delete cinema from the database by id
     * @param username admin username
     * @param accessKey admin access key
     * @param id cinema id
     * @return Response code 200
     * @Note To call the method, you need to enter administrator data in the path
     */
    @DeleteMapping("/{username}/{accessKey}/{id}")
    public ResponseEntity<?> deleteCinemaById(@PathVariable final String username, @PathVariable final String accessKey,
                                              @PathVariable final Long id) {
        cinemaService.deleteCinemaById(username, accessKey, id);
        return ResponseEntity.ok().build();
    }
}
