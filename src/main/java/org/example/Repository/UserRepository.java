package org.example.Repository;

import org.example.Entity.User;
import org.example.Exception.ServiceException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private List<User> users;
    private Long id = 0L;

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

    public void deleteUserById(final Long id) {
        users = users.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public User updateAllUser(final User user) {
        final User saved = getUserById(user.getId());

        saved.setName(user.getName());
        saved.setSurname(user.getSurname());
        saved.setEmail(user.getEmail());
        saved.setPassword(user.getPassword());

        return saved;
    }
}
