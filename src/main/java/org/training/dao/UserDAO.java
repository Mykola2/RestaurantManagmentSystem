package org.training.dao;

import org.training.model.entities.User;

import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public interface UserDAO {
    User find(int id);

    void create(User user);

    User findByLogin(String login);

    User findByEmail(String email);

    void withdraw(Double totalprice, Integer userId);

    List<User> getAll();

    void setBalance(Double balance, Integer userId);
}
