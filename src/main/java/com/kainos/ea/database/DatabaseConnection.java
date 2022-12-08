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

    public Connection getConnection() throws SQLException, DatabaseConnectionException {

        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        else {
            try {
                final String user            = System.getenv("DB_USERNAME");
                final String password        = System.getenv("DB_PASSWORD");
                final String host            = System.getenv("DB_HOST");
                final String db              = System.getenv("DB_NAME");

                System.out.println("!! " + user);
                System.out.println("!! " + password);
                System.out.println("!! " + host);
                System.out.println("!! " + db);



                if (user == null || password == null || host == null || db == null)
                    throw new IllegalArgumentException(
                            "Environment variables not set.");

                //conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db + "?useSSL=false", user, password);
                conn = DriverManager.getConnection("jdbc:mysql://"
                        + host + "/" + db + "?allowPublicKeyRetrieval=true&useSSL=false", user, password);
                return conn;

            } catch (Exception e) {
                throw new DatabaseConnectionException(e);
            }

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
