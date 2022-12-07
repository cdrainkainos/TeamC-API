package com.kainos.ea.service;

import com.kainos.ea.dao.UserDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.UserAlreadyExistsException;
import com.kainos.ea.model.User;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {

    public UserDao userDao;
    public DatabaseConnection databaseConnector;

    public UserService(UserDao userDao, DatabaseConnection databaseConnector){
        this.userDao = userDao;
        this.databaseConnector = databaseConnector;

    }

    public User registerUser(User user) throws SQLException, DatabaseConnectionException, IOException, UserAlreadyExistsException {
        databaseConnector.closeConnection();
        return userDao.registerUser(databaseConnector.getConnection(), user);
    }
}