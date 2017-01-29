package org.training.model.dao;

import org.training.model.entities.User;

/**
 * Created by nicko on 1/25/2017.
 */
public interface UserDAO {
    User find(int id);

    void create(User user);

    void update(User user);

    void delete(User user);

    User findByLogin(String login);

    void withdraw(Double totalprice, Integer userId);
}
