package org.example.Repository;

import org.example.Entity.Hall;
import org.example.Exception.ServiceException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HallRepository {
    private List<Hall> halls;
    private Long id = 0L;

    public List<Hall> getAllHalls() {
        return new ArrayList<>(halls);
    }

    public void saveHall(final Hall hall) {
        hall.setId(id);
        ++id;
        halls.add(hall);
    }

    public Hall getHallById(final Long id) {
        return halls.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "hall with id " + id + " not found"));
    }

    public void deleteHallById(final Long id) {
        halls = halls.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Hall updateAllHall(final Hall hall) {
        final Hall saved = getHallById(hall.getId());

        saved.setName(hall.getName());
        saved.setFilms(hall.getFilms());

        return saved;
    }
}
