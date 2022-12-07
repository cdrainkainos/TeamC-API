package com.kainos.ea.service;

import com.kainos.ea.dao.UserDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.UserAlreadyExistsException;
import com.kainos.ea.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    UserDao userDao = Mockito.mock(UserDao.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    UserService userService = new UserService(userDao, databaseConnector);

    Connection conn;

    @Test
    void registerUser_shouldReturnUser_whenDaoReturnsUser() throws SQLException, DatabaseConnectionException, IOException, UserAlreadyExistsException {
        User user1 = new User();
        User user2 = new User();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(userDao.registerUser(conn, user1)).thenReturn(user1);
        user2 = userService.registerUser(user1);
        assertEquals(user1, user2);
    }

    @Test
    void registerUser_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException, UserAlreadyExistsException {
        User user1 = new User();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(userDao.registerUser(conn, user1)).thenThrow(UserAlreadyExistsException.class);
        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(user1));
    }
}
