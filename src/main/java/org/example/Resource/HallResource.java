package org.example.Resource;

import org.example.DTO.HallDTO;
import org.example.Service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hall")
public class HallResource {
    @Autowired
    private HallService hallService;

    @GetMapping
    public ResponseEntity<List<HallDTO>> getAllHalls() {
        return ResponseEntity.ok().body(hallService.getAllHalls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallDTO> getHallById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(hallService.getHallById(id));
    }

    @PostMapping
    public ResponseEntity<HallDTO> saveHall(@RequestBody @Validated final HallDTO hallDTO) {
        return ResponseEntity.ok().body(hallService.saveHall(hallDTO));
    }

    @PutMapping
    public ResponseEntity<HallDTO> updateAllHall(@RequestBody @Validated final HallDTO hallDTO) {
        return ResponseEntity.ok().body(hallService.updateAllHall(hallDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHallById(@PathVariable final Long id) {
        hallService.deleteHallById(id);
        return ResponseEntity.ok().build();
    }
}
