package org.example.Resource;

import org.example.DTO.FilmDTO;
import org.example.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/film")
public class FilmResource {
    @Autowired
    private FilmService filmService;

    @GetMapping("/{role}/{username}/{password}")
    public ResponseEntity<List<FilmDTO>> getAllFilms(@PathVariable final String role, @PathVariable final String username,
                                                     @PathVariable final String password) {
        return ResponseEntity.ok().body(filmService.getAllFilms(role, username, password));
    }

    @GetMapping("/{role}/{username}/{password}/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable final String role, @PathVariable final String username,
                                               @PathVariable final String password, @PathVariable final Long id) {
        return ResponseEntity.ok().body(filmService.getFilmById(role, username, password, id));
    }

    @PostMapping("/{username}/{accessKey}")
    public ResponseEntity<FilmDTO> saveFilm(@PathVariable final String username, @PathVariable final String accessKey,
                                            @RequestBody @Validated final FilmDTO filmDTO) {
        return ResponseEntity.ok().body(filmService.saveFilm(username, accessKey, filmDTO));
    }

    @PutMapping("/{username}/{accessKey}")
    public ResponseEntity<FilmDTO> updateAllFilm(@PathVariable final String username, @PathVariable final String accessKey,
                                                 @RequestBody @Validated final FilmDTO filmDTO) {
        return ResponseEntity.ok().body(filmService.updateAllFilm(username, accessKey, filmDTO));
    }

    @DeleteMapping("/{username}/{accessKey}/{id}")
    public ResponseEntity<?> deleteFilmById(@PathVariable final String username, @PathVariable final String accessKey,
                                            @PathVariable final Long id) {
        filmService.deleteFilmById(username, accessKey, id);
        return ResponseEntity.ok().build();
    }
}
