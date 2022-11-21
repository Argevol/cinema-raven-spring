package org.example.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Entity.Administrator;
import org.example.Entity.Enumeration.Role;
import org.example.Entity.Film;
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
public class AdministratorRepository {
    private List<Administrator> administrators;
    private Long id = 0L;

    @PostConstruct
    public void init() {
        final Path file = Paths.get("administrators.txt");

        try {
            final String savedFilmsAsString = Files.readString(file, StandardCharsets.UTF_16);
            administrators = JacksonUtil.deserialize(savedFilmsAsString, new TypeReference<List<Administrator>>() {
            });

            if (administrators == null) {
                administrators = new ArrayList<>();
                return;
            }

            final long maxId = administrators.stream().mapToLong(Administrator::getId).max().orElse(-1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue");
            administrators = new ArrayList<>();
        }
    }

    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("administrators.txt");

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(administrators)), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue");
        }
    }

    public List<Administrator> getAllAdministrators() {
        return new ArrayList<>(administrators);
    }

    public void saveAdministrator(final Administrator administrator) {
        administrator.setId(id);
        ++id;
        administrators.add(administrator);
    }

    public Administrator getAdministratorById(final Long id) {
        return administrators.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "administrator with id " + id + " not found"));
    }

    public Administrator getAdministratorByUsername(final String username) {
        return administrators.stream()
                .filter(e -> e.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "administrator with username " + username + " not found"));
    }

    public void deleteAdministratorById(final Long id) {
        administrators = administrators.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Administrator updateAdministratorData(final Administrator administrator) {
        final Administrator saved = getAdministratorById(administrator.getId());

        saved.setUsername(administrator.getUsername());
        saved.setPassword(administrator.getPassword());
        saved.setAccessKey(administrator.getAccessKey());
        saved.setRole(Role.ADMIN);

        return saved;
    }
}
