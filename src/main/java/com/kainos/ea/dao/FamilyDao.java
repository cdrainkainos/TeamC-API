package com.kainos.ea.dao;

import com.kainos.ea.model.JobFamily;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyDao {

    public FamilyDao(){
    }

    public int updateJobFamilyCapability(JobFamily jobFamily, Connection c) throws SQLException{

        String updateQuery = "UPDATE job_family " +
                "SET capability_id = ? " +
                "WHERE id = ?";

        PreparedStatement prepStm = c.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
        prepStm.setInt(1, jobFamily.getCapabilityId());
        prepStm.setInt(2, jobFamily.getId());

        int affectedRows = prepStm.executeUpdate();

        if (affectedRows == 0){
            throw new SQLException("Update failed, no rows affected.");
        }

        int famId = 0;

        try(ResultSet rs = prepStm.getGeneratedKeys()){
            if (rs.next()){
                famId = rs.getInt(1);
            } else {
                famId = jobFamily.getId();
            }
        }
        return famId;
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
