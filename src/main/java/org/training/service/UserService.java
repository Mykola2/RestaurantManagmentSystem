package org.training.service;

import org.training.model.entities.User;

/**
 * Created by nicko on 1/25/2017.
 */
public interface UserService {
    void create(User user);

    void delete(Integer id);

    void update(User user);

    User find(Integer id);

    User findByLogin(String login);

    void withdraw(Double totalprice, Integer userId);
}
