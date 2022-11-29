package com.kainos.ea.dao;

import com.kainos.ea.exception.RoleNotExistException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecification;

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
    public List<JobSpecification> getAllSpecification(Connection c, int role_id) throws SQLException, RoleNotExistException {

        try{
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT kainos_job_title, job_specification, job_spec_link  FROM job_role Where id =" +role_id );
            List<JobSpecification> jobSpecifications = new ArrayList<>();

            while (rs.next()) {
                JobSpecification jobSpecification = new JobSpecification(
                        rs.getString("kainos_job_title"),
                        rs.getString("job_specification"),
                        rs.getString("job_spec_link"));
                jobSpecifications.add(jobSpecification);
            }

            if(jobSpecifications.isEmpty()){
                throw new RoleNotExistException("Select record that is not empty");
            } else return jobSpecifications;

        } catch (SQLException e) {
            throw new SQLException ("Select query for job specifications is incorrect");
        }
    }
}

