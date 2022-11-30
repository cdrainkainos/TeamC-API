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
            ResultSet rs = st.executeQuery("SELECT job_role.id, job_role.kainos_job_title, capability.capability_name, band.band_name\n" +
                    "FROM job_role JOIN job_family\n" +
                    "ON (job_role.job_family_id = job_family.id)\n" +
                    "JOIN capability " +
                    "ON (job_family.capability_id = capability.id)" +
                    "JOIN band " +
                    "ON job_role.band_id=band.id;");

            List<JobRole> jobRoles = new ArrayList<>();

            while (rs.next()) {
                JobRole role = new JobRole(
                        rs.getInt("id"),
                        rs.getString("kainos_job_title"),
                        rs.getString("capability_name"),
                        rs.getString("band_name"));
                jobRoles.add(role);
            }
            return jobRoles;
        } catch (SQLException e) {
            throw new SQLException ("Error with sql statement");
        }

    }
    public JobSpecification getAllSpecification(Connection c, int role_id) throws SQLException, RoleNotExistException {

        try{
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT kainos_job_title, job_specification, job_spec_link  FROM job_role Where id =" +role_id );
            JobSpecification jobSpecification = null;
            while (rs.next()) {
                 jobSpecification = new JobSpecification(
                        rs.getString("kainos_job_title"),
                        rs.getString("job_specification"),
                        rs.getString("job_spec_link"));
            }
            if(jobSpecification == null){
                throw new RoleNotExistException("Select record that is not empty");
            } else return jobSpecification;


        } catch (SQLException e) {
            throw new SQLException ("Select query for job specifications is incorrect");
        }
    }
}

