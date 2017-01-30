package org.training.service;

import org.training.model.entities.User;

/**
 * Created by nicko on 1/25/2017.
 */
public interface UserService {
    User create(String login, String password, String email, Integer role);

    User find(Integer id);

    User findByLogin(String login);

    User login(String login, String password);

    void withdraw(Double totalprice, Integer userId);
}
