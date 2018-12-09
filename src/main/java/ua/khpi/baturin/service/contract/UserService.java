package ua.khpi.baturin.service.contract;

import java.util.List;

import ua.khpi.baturin.entity.User;

public interface UserService {

    void create(User user);

    void update(User user);

    void remove(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findByEmail(String email);

    User findById(Long id);
}
