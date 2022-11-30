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
            ResultSet rs = st.executeQuery("SELECT job_role.id, job_role.kainos_job_title, capability.capability_name\n" +
                    "FROM job_role JOIN job_family\n" +
                    "ON (job_role.job_family_id = job_family.id)\n" +
                    "JOIN capability " +
                    "ON (job_family.capability_id = capability.id);");

            List<JobRole> jobRoles = new ArrayList<>();

            while (rs.next()) {
                JobRole role = new JobRole(
                        rs.getInt("id"),
                        rs.getString("kainos_Job_Title"),
                        rs.getString("capability_name"));
                jobRoles.add(role);
            }
            return jobRoles;
        } catch (SQLException e) {
            throw new SQLException ("Error with sql statement");
        }

    }
}
