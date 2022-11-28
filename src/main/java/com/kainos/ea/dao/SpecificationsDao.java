package com.kainos.ea.dao;


import com.kainos.ea.model.JobSpecification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpecificationsDao {

    public SpecificationsDao() {
    }

    public List<JobSpecification> getAllSpecification(Connection c, int role_id) throws SQLException {

        try{
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, job_specification, job_spec_link  FROM job_role Where id =" +role_id );
            List<JobSpecification> jobSpecifications = new ArrayList<>();

            while (rs.next()) {
                JobSpecification jobSpecification = new JobSpecification(
                        rs.getInt("id"),
                        rs.getString("job_specification"),
                        rs.getString("job_spec_link"));
                jobSpecifications.add(jobSpecification);
            }
            return jobSpecifications;
        } catch (SQLException e) {
            throw new SQLException ("Select query for job specifications is incorrect");
        }
    }
}
