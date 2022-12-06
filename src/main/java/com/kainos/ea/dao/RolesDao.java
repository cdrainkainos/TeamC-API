package com.kainos.ea.dao;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleXL;
import java.sql.*;
import com.kainos.ea.exception.RoleNotExistException;
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

            ResultSet rs = st.executeQuery("SELECT job_role.id, job_role.band_id, job_role.kainos_job_title, capability.capability_name, band.band_name\n" +
                    "FROM job_role JOIN job_family\n" +
                    "ON (job_role.job_family_id = job_family.id)\n" +
                    "JOIN capability " +
                    "ON (job_family.capability_id = capability.id)" +
                    "JOIN band " +
                    "ON job_role.band_id=band.id " +
                    "ORDER BY job_role.id;");

            List<JobRole> jobRoles = new ArrayList<>();

            while (rs.next()) {
                JobRole role = new JobRole(
                        rs.getInt("id"),
                        rs.getString("kainos_job_title"),
                        rs.getString("band_name"),
                        rs.getInt("band_id"),
                        rs.getString("capability_name"));
                jobRoles.add(role);
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

    public int updateJobRole(JobRoleXL jobRoleXL, Connection c) throws SQLException{

        String updateQuery = "UPDATE job_role SET" +
                " band_id = ?," +
                " job_family_id = ?," +
                " kainos_job_title = ?," +
                " job_specification = ?, " +
                " job_spec_link = ? " +
                "WHERE id = ?";

        PreparedStatement prepStm = c.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
        prepStm.setInt(1, jobRoleXL.getBandId());
        prepStm.setInt(2, jobRoleXL.getJobFamilyId());
        prepStm.setString(3, jobRoleXL.getRole_title());
        prepStm.setString(4, jobRoleXL.getJobSpecification());
        prepStm.setString(5, jobRoleXL.getJobSpecLink());
        prepStm.setInt(6, jobRoleXL.getId());

        int affectedRows = prepStm.executeUpdate();

        if (affectedRows == 0){
            throw new SQLException("Update failed, no rows affected.");
        }

        int recordId;

        try(ResultSet rs = prepStm.getGeneratedKeys()){
            if (rs.next()){
                recordId = rs.getInt(1);
            } else {
                recordId = jobRoleXL.getId();
            }
        }
        return recordId;
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
