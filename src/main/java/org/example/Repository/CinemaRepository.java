package org.example.Repository;

import org.example.Entity.Cinema;
import org.example.Exception.ServiceException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CinemaRepository {
    private List<Cinema> cinemas;
    private Long id = 0L;

    public List<Cinema> getAllCinemas() {
        return new ArrayList<>(cinemas);
    }

    public void saveCinema(final Cinema cinema) {
        cinema.setId(id);
        ++id;
        cinemas.add(cinema);
    }

    public Cinema getCinemaById(final Long id) {
        return cinemas.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "cinema with id " + id + " not found"));
    }

    public void deleteCinemaById(final Long id) {
        cinemas = cinemas.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Cinema updateAllCinema(final Cinema cinema) {
        final Cinema saved = getCinemaById(cinema.getId());

        saved.setName(cinema.getName());
        saved.setMapOfAllFilmSessions(cinema.getMapOfAllFilmSessions());

        return saved;
    }
}
