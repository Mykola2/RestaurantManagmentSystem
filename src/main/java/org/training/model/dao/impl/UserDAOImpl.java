package org.training.model.dao.impl;

import org.training.model.dao.UserDAO;
import org.training.model.entities.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nicko on 1/25/2017.
 */
public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public static final String SELECT_ONE_BY_ID = "SELECT * from user where id = ?";
    public static final String SELECT_ONE_BY_LOGIN = "SELECT * from user where login = ?";
    public static final String CREATE_USER = "INSERT INTO user (`login`, `email`, `Role_idRole`, `password`) VALUES (?,?,?,?)";

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User find(int id) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ONE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                resultSet.next();
                user = new User(resultSet.getString("login"), resultSet.getString("password"),
                        resultSet.getString("email"), resultSet.getInt("Role_idRole"));
                return user;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error by finding user", e);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ONE_BY_LOGIN)) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                resultSet.next();
                System.out.println(resultSet.getString(1));
                user = new User(resultSet.getInt("idClient"), resultSet.getString("login"), resultSet.getString("password"),
                        resultSet.getString("email"), resultSet.getInt("Role_idRole"));

                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error by finding user", e);
        }
        return user;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getRole().ordinal());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
