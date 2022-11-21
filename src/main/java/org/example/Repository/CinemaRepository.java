package org.example.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Entity.Cinema;
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
public class CinemaRepository {
    private List<Cinema> cinemas;
    private Long id = 0L;

    @PostConstruct
    public void init() {
        final Path file = Paths.get("cinemas.txt");

        try {
            final String savedCinemasAsString = Files.readString(file, StandardCharsets.UTF_16);
            cinemas = JacksonUtil.deserialize(savedCinemasAsString, new TypeReference<List<Cinema>>() {
            });

            if (cinemas == null) {
                cinemas = new ArrayList<>();
                return;
            }

            final long maxId = cinemas.stream().mapToLong(Cinema::getId).max().orElse(-1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue");
            cinemas = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("cinemas.txt");

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(cinemas)), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue");
        }
    }

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
