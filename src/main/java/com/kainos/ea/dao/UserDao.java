package com.kainos.ea.dao;

import com.kainos.ea.exception.UserAlreadyExistsException;
import com.kainos.ea.model.User;
import java.sql.*;
public class UserDao {

    public User registerUser(Connection conn, User user) throws SQLException {

        User returnedUser = getUser(conn, user);

        if (returnedUser.getId() != 0) {
            User nullUser = new User();
            return nullUser;
        } else {
            String insertUserQuery = "INSERT INTO users (email, password, role)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, user.getEmail());
            preparedStmt.setString(2, user.getPassword());
            preparedStmt.setString(3, user.getRole());

            int affectedRows = preparedStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            returnedUser = user;


        }

        return returnedUser;

    }

    public User getUser(Connection conn, User user) throws SQLException {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE email = \"" + user.getEmail() + "\";");

            User user1 = new User();

        while (rs.next()) {
            user1.setId(rs.getInt("id"));
            user1.setEmail(rs.getString("email"));
            user1.setPassword(rs.getString("password"));
            user1.setRole(rs.getString("role"));

        }
            return user1;
        } catch (SQLException e) {
            throw new SQLException("Error with sql statement");
        }
    }
}
