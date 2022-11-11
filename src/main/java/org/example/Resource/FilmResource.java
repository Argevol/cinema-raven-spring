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

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllItems() {
        return ResponseEntity.ok().body(filmService.getAllFilms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(filmService.getFilmById(id));
    }

    @PostMapping
    public ResponseEntity<FilmDTO> saveFilm(@RequestBody @Validated final FilmDTO filmDTO) {
        return ResponseEntity.ok().body(filmService.saveFilm(filmDTO));
    }

    @PutMapping
    public ResponseEntity<FilmDTO> updateAllFilm(@RequestBody @Validated final FilmDTO filmDTO) {
        return ResponseEntity.ok().body(filmService.updateAllFilm(filmDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilmById(@PathVariable final Long id) {
        filmService.deleteFilmById(id);
        return ResponseEntity.ok().build();
    }
}
