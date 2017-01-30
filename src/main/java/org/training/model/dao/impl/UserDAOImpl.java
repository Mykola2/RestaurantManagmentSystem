package org.training.model.dao.impl;

import org.training.model.dao.UserDAO;
import org.training.model.dao.exception.DAOException;
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
    public static final String SELECT_ONE_BY_EMAIL = "SELECT * from user where email = ?";
    public static final String CREATE_USER = "INSERT INTO user (`login`, `email`, `Role_idRole`, `password`,`balance`) VALUES (?,?,?,?,?)";
    private static final String WITHDRAW = "UPDATE user set balance = balance - ? where idClient = ?";

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User find(int id) {
        return getUserBy(id, SELECT_ONE_BY_ID);
    }

    @Override
    public User findByLogin(String login) {
        return getUserBy(login, SELECT_ONE_BY_LOGIN);
    }

    @Override
    public User findByEmail(String email) {
        return getUserBy(email, SELECT_ONE_BY_EMAIL);
    }

    private User getUserBy(String parameter, String query) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    private User getUserBy(Integer parameter, String query) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public void withdraw(Double totalprice, Integer userId) {
        try (PreparedStatement statement = connection.prepareStatement(WITHDRAW)) {
            statement.setDouble(1, totalprice);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getRole().ordinal() + 1);
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDouble(5, user.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user;
            user = new User();
            user.setId(resultSet.getInt("idClient"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setRole(resultSet.getInt("Role_idRole"));
            user.setBalance(resultSet.getDouble("balance"));
            return user;
        }
        return null;
    }
}
