package org.training.service;

import org.training.model.entities.User;

import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public interface UserService {
    User create(User user);

    User find(Integer id);

    User findByLogin(String login);

    User login(String login, String password);

    void withdraw(Double totalprice, Integer userId);

    void setBalance(Double balance, Integer userId);

    List<User> getAllUsers();
}
