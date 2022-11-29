package org.example.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.Entity.Administrator;
import org.example.Entity.Enumeration.Role;
import org.example.Entity.User;
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
public class UserRepository {
    private List<User> users;
    private Long id = 0L;

    /**
     * Reading a file with saved users data
     * @apiNote The method is called before starting the database.
     * The program looks for the largest ID, from which the creation of future users begins
     */
    @PostConstruct
    public void init() {
        final Path file = Paths.get("users.txt");

        try {
            final String savedFilmsAsString = Files.readString(file, StandardCharsets.UTF_16);
            users = JacksonUtil.deserialize(savedFilmsAsString, new TypeReference<List<User>>() {
            });

            if (users == null) {
                users = new ArrayList<>();
                return;
            }

            final long maxId = users.stream().mapToLong(User::getId).max().orElse(-1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue");
            users = new ArrayList<>();
        }
    }

    /**
     * Write the data about the users that were created to the file
     */
    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("users.txt");

        try {
            Files.writeString(file, Objects.requireNonNull(JacksonUtil.serialize(users)), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue");
        }
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void saveUser(final User user) {
        user.setId(id);
        ++id;
        users.add(user);
    }

    public User getUserById(final Long id) {
        return users.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "user with id " + id + " not found"));
    }

    public User getUserByUsername(final String username) {
        return users.stream()
                .filter(e -> e.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "user with username " + username + " not found"));
    }

    public User getBo(final String username) {
        return users.stream()
                .filter(e -> e.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "user with username " + username + " not found"));
    }

    public void deleteUserById(final Long id) {
        users = users.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public User updateUserData(final User user) {
        final User saved = getUserById(user.getId());

        saved.setUsername(user.getUsername());
        saved.setPassword(user.getPassword());
        saved.setRole(Role.USER);

        return saved;
    }
}
