package org.example.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Entity.Film;
import org.example.Entity.Hall;
import org.example.Exception.ServiceException;
import org.example.Utils.JacksonUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class HallRepository {
    private List<Hall> halls;
    private Long id = 0L;

    @PostConstruct
    public void init() {
        final Path file = Paths.get("halls.txt");

        try {
            final String savedHallsAsString = Files.readString(file, StandardCharsets.UTF_16);
            halls = JacksonUtil.deserialize(savedHallsAsString, new TypeReference<List<Hall>>() {
            });

            if (halls == null) {
                halls = new ArrayList<>();
                return;
            }

            final long maxId = halls.stream().mapToLong(Hall::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue");
            halls = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("halls.txt");

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(halls)), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue");
        }
    }

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
