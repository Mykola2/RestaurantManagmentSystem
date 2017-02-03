package service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.training.dao.UserDAO;
import org.training.dao.connection.ConnectionManager;
import org.training.dao.connection.MySQLConnection;
import org.training.dao.exception.DAOException;
import org.training.dao.factory.DAOFactory;
import org.training.model.entities.User;
import org.training.service.UserService;
import org.training.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nicko on 2/2/2017.
 */
public class UserServiceTest {
    @Mock
    private DAOFactory daoFactory;

    @Mock
    private MySQLConnection connection;

    @Mock
    private ConnectionManager connectionManager;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService service = UserServiceImpl.getInstance();

    @Before
    public void init() throws DAOException, SQLException {
        MockitoAnnotations.initMocks(this);

        when(connectionManager.getMySQLConnection()).thenReturn(connection);
        when(daoFactory.getUserDAO(connection)).thenReturn(userDAO);
        User testUser = new User.Builder()
                .setId(1)
                .setLogin("testUser")
                .setPassword("1")
                .setRole(1)
                .build();
        when(userDAO.findByLogin(anyString())).thenReturn(testUser);
    }

    @Test
    public void testLogin(){
        service.findByLogin("testUser");
        verify(userDAO,times(1)).findByLogin("testUser");
    }

}
