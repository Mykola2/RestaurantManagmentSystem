package org.training.model.dao.impl;

import org.training.model.dao.UserDAO;
import org.training.model.entities.User;

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
    private static final String WITHDRAW = "UPDATE user set balance = balance - ? where idClient = ?";

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
                user = new User();
                user.setId(resultSet.getInt("idClient"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail( resultSet.getString("email"));
                user.setRole(resultSet.getInt("Role_idRole"));
                user.setBalance(resultSet.getDouble("balance"));
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
                user = new User();
                user.setId(resultSet.getInt("idClient"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail( resultSet.getString("email"));
                user.setRole(resultSet.getInt("Role_idRole"));
                user.setBalance(resultSet.getDouble("balance"));

                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error by finding user", e);
        }
        return user;
    }

    @Override
    public void withdraw(Double totalprice, Integer userId) {
        try (PreparedStatement statement = connection.prepareStatement(WITHDRAW)) {
            statement.setDouble(1,totalprice);
            statement.setInt(2,userId);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
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
