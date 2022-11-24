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

            List<JobRole> job_roles = new ArrayList<>();

            while (rs.next()) {
                JobRole role = new JobRole(
                        rs.getInt("id"),
                        rs.getString("Kainos_Job_Title"));
                job_roles.add(role);
            }
            return job_roles;
        } catch (SQLException e) {
            throw new SQLException ("Select query for roles is incorrect");
        }

    }
}
