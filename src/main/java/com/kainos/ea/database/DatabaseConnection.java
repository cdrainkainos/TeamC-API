package com.kainos.ea.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {

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
            e.printStackTrace();
            closeConnection();
        }

        return null;
    }
    public static void closeConnection()  {
        try{
            if (conn != null) {
                conn.close();
                conn = null;
            }
        }catch (SQLException e ){
            e.printStackTrace();
            // do something
        }
    }
}
