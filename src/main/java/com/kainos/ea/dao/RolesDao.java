package com.kainos.ea.dao;

import com.kainos.ea.model.Job_Role;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolesDao {
    public RolesDao(){
        //empty constructor
    }

    /*
    Method to return all job roles as a list
     */
    public List<Job_Role> getAllRoles(Connection c) throws SQLException {
        try{
            //create and execute simple sql statement to get id and job title from the database
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT id, Kainos_Job_Title "
                            + "FROM Job_Roles;");

            //new empty array list called job_roles
            List<Job_Role> job_roles = new ArrayList<>();

            //iterate though the result set adding db data to the list
            while (rs.next()) {
                Job_Role role = new Job_Role(
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