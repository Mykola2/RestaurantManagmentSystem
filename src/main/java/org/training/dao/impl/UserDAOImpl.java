package org.training.dao.impl;

import org.apache.log4j.Logger;
import org.training.dao.UserDAO;
import org.training.dao.exception.DAOException;
import org.training.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicko on 1/25/2017.
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private Connection connection;

    public static final String SELECT_ONE_BY_ID = "SELECT * from user where id = ?";
    public static final String SELECT_ONE_BY_LOGIN = "SELECT * from user where login = ?";
    public static final String SELECT_ONE_BY_EMAIL = "SELECT * from user where email = ?";
    public static final String CREATE_USER = "INSERT INTO user (`login`, `email`, `Role_idRole`, `password`,`balance`) VALUES (?,?,?,?,?)";
    private static final String WITHDRAW = "UPDATE user set balance = balance - ? where idClient = ?";
    private static final String SET_BALANCE = "UPDATE user set balance = ? where idClient = ?";
    private static final String SELECT_ALL_USERS = "SELECT * from user where Role_idRole = 2";

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
            logger.error("Error by getting user",e);
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
            logger.error("Error by getting user",e);
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
            logger.error("Error by withdraw balance",e);
            throw new DAOException(e);
        }
    }

    @Override
    public void setBalance(Double balance, Integer userId) {
        try (PreparedStatement statement = connection.prepareStatement(SET_BALANCE)) {
            statement.setDouble(1, balance);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error setting balance",e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User.Builder()
                        .setId(resultSet.getInt("idClient"))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setRole(resultSet.getInt("Role_idRole"))
                        .setBalance(resultSet.getDouble("balance"))
                        .build();
                items.add(user);
            }
            return items;
        } catch (SQLException e) {
            logger.error("Error retrieving all users", e);
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
            logger.error("Error creating user",e);
            throw new DAOException(e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User.Builder()
                    .setId(resultSet.getInt("idClient"))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .setEmail(resultSet.getString("email"))
                    .setRole(resultSet.getInt("Role_idRole"))
                    .setBalance(resultSet.getDouble("balance"))
                    .build();
            return user;
        }
        return null;
    }
}
