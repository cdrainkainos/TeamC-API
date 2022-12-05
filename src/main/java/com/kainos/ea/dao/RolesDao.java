package com.kainos.ea.dao;
import com.kainos.ea.model.JobRoleResponse;
import com.kainos.ea.model.JobRoleRequest;
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

    public List<JobRoleResponse> getAllRoles(Connection c) throws SQLException {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT job_role.id, job_role.kainos_job_title, capability.capability_name, band.band_name\n" +
                    "FROM job_role JOIN job_family\n" +
                    "ON (job_role.job_family_id = job_family.id)\n" +
                    "JOIN capability " +
                    "ON (job_family.capability_id = capability.id)" +
                    "JOIN band " +
                    "ON job_role.band_id=band.id " +
                    "ORDER BY job_role.id;");

            List<JobRoleResponse> jobRoles = new ArrayList<>();

            while (rs.next()) {
                JobRoleResponse role = new JobRoleResponse(
                        rs.getInt("id"),
                        rs.getString("kainos_job_title"),
                        rs.getString("band_name"),
                        rs.getString("capability_name"));
                jobRoles.add(role);
            }
            return jobRoles;
        } catch (SQLException e) {
            throw new SQLException ("Error with sql statement");
        }
    }

    public JobRoleRequest getRoleById(int roleID, Connection c) throws SQLException {

        String query = String.format("SELECT band_id, job_family_id, kainos_job_title, job_specification, job_spec_link" +
                " FROM job_role WHERE id = %d", roleID);
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        if (resultSet.next()){
            JobRoleRequest jobRoleRequest = new JobRoleRequest(
                    roleID,
                    resultSet.getInt("band_id"),
                    resultSet.getInt("job_family_id"),
                    resultSet.getString("kainos_job_title"),
                    resultSet.getString("job_specification"),
                    resultSet.getString("job_spec_link")
            );
            return jobRoleRequest;
        }
        return null;
    }

    public boolean updateJobRole(int roleID, JobRoleRequest jobRoleRequest, Connection c) throws SQLException{

        String updateQuery = "UPDATE job_role SET" +
                " band_id = ?," +
                " job_family_id = ?," +
                " kainos_job_title = ?," +
                " job_specification = ?, " +
                " job_spec_link = ? " +
                "WHERE id = ?";

        PreparedStatement prepStm = c.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
        prepStm.setInt(1, jobRoleRequest.getBandId());
        prepStm.setInt(2, jobRoleRequest.getJobFamilyId());
        prepStm.setString(3, jobRoleRequest.getRole_title());
        prepStm.setString(4, jobRoleRequest.getJobSpecification());
        prepStm.setString(5, jobRoleRequest.getJobSpecLink());
        prepStm.setInt(6, roleID);

        int affectedRows = prepStm.executeUpdate();

        if (affectedRows == 0){
            throw new SQLException("Update failed, no rows affected.");
        }

        return true;
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
