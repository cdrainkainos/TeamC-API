package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolesDao {
    public RolesDao(){
    }

    public List<JobRole> getAllRoles(Connection c) throws SQLException {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT id, Kainos_Job_Title "
                            + "FROM Job_Roles;");

            List<JobRole> jobRoles = new ArrayList<>();

            while (rs.next()) {
                jobRoles.add(new JobRole(rs.getInt("id"), rs.getString("Kainos_Job_Title")));
            }
            return jobRoles;
        } catch (SQLException e) {
            throw new SQLException ("Error with sql statement");
        }

    }
}
