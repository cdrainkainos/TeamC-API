package com.kainos.ea.dao;

import com.kainos.ea.model.JobFamily;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyDao {

    public FamilyDao(){
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
