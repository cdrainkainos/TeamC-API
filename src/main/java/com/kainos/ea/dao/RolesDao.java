package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleXL;

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
                    "SELECT id, kainos_job_title "
                            + "FROM job_role;");

            List<JobRole> jobRoles = new ArrayList<>();

            while (rs.next()) {
                jobRoles.add(new JobRole(rs.getInt("id"), rs.getString("kainos_job_title")));
            }
            return jobRoles;
        } catch (SQLException e) {
            throw new SQLException ("Error with sql statement");
        }

    }

    public JobRoleXL getRoleById(int roleID, Connection c) throws SQLException {

        String query = String.format("SELECT band_id, job_family_id, kainos_job_title, job_specification, job_spec_link" +
                " FROM job_role WHERE id = %d", roleID);
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        if (resultSet.next()){
            JobRoleXL jobRoleXL = new JobRoleXL(
                    roleID,
                    resultSet.getInt("band_id"),
                    resultSet.getInt("job_family_id"),
                    resultSet.getString("kainos_job_title"),
                    resultSet.getString("job_specification"),
                    resultSet.getString("job_spec_link")
            );
            return jobRoleXL;
        }
        return null;
    }


}
