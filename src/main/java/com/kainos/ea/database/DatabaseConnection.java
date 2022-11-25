package com.kainos.ea.database;

import com.kainos.ea.exception.DatabaseConnectionException;
import org.eclipse.jetty.util.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection conn;

    public Connection getConnection() throws SQLException, DatabaseConnectionException, IOException {

        if (conn != null) {
            return conn;
        }

        try {
            FileInputStream propsStream = new FileInputStream("employeedb.properties");
            Properties props = new Properties();
            props.load(propsStream);

            final String user            = props.getProperty("user");
            final String password        = props.getProperty("password");
            final String host            = props.getProperty("host");

            if (user == null || password == null || host == null) throw new IllegalArgumentException("Properties file must exist and must contain " + "user, password, and host properties.");

            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/AgileSprints_SamB?useSSL=false", user, password);
            return conn;

        } catch (SQLException | IOException e) {
            throw new IOException();
        }

    }
    public void closeConnection() throws DatabaseConnectionException {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e ) {
            throw new DatabaseConnectionException(e);

        }
    }
}
