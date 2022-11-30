package com.kainos.ea.dao;

import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobFamily;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FamilyDao {

    public FamilyDao(){
    }

    public JobFamily getFamilyById(int familyID, Connection c) throws SQLException {

        String query = String.format("SELECT capability_id, job_family FROM job_family WHERE id = %d", familyID);
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        if (resultSet.next()){
            JobFamily jobFamily = new JobFamily(
                    familyID,
                    resultSet.getInt("capability_id"),
                    resultSet.getString("job_family")
            );
            return jobFamily;
        }
        return null;
    }

    public List<JobFamily> getAllFamilies(Connection c) throws SQLException {

        String query = "SELECT * FROM job_family";
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        List<JobFamily> jobFamilies = new ArrayList<>();

        while (resultSet.next()) {
            jobFamilies.add(new JobFamily(
                    resultSet.getInt("id"),
                    resultSet.getInt("capability_id"),
                    resultSet.getString("job_family")
            ));
        }
        return jobFamilies;
    }
}
