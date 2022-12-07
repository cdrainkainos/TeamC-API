package com.kainos.ea.service;
import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.JobRoleResponse;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.exception.RoleNotExistException;
import com.kainos.ea.model.JobSpecification;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class RolesService {
    public RolesDao rolesDao;
    public DatabaseConnection databaseConnector;

    public RolesService(RolesDao rolesDao, DatabaseConnection databaseConnector){
        this.rolesDao = rolesDao;
        this.databaseConnector = databaseConnector;

    }

    public boolean updateJobRole(int roleID, JobRoleRequest jobRoleRequest) throws SQLException, DatabaseConnectionException, IOException {
        boolean updateSuccessful = rolesDao.updateJobRole(roleID, jobRoleRequest, databaseConnector.getConnection());
        databaseConnector.closeConnection();
        return updateSuccessful;

    }

    public List<JobRoleResponse> getAllRoles() throws SQLException, DatabaseConnectionException, IOException {
        List allJobRoles = rolesDao.getAllRoles(databaseConnector.getConnection());
        databaseConnector.closeConnection();
        return allJobRoles;
    }

    public JobRoleRequest getRoleById(int roleID) throws DatabaseConnectionException, SQLException, IOException, JobRoleDoesNotExistException {
        JobRoleRequest jobRoleRequest = rolesDao.getRoleById(roleID, databaseConnector.getConnection());
        if (Objects.isNull(jobRoleRequest)) {
            throw new JobRoleDoesNotExistException();
        }
        databaseConnector.closeConnection();
        return jobRoleRequest;
    }

    public JobSpecification getAllSpecifications(int role_id) throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        JobSpecification allSpecifications = rolesDao.getAllSpecification(databaseConnector.getConnection(), role_id);
        databaseConnector.closeConnection();
        return allSpecifications;

    }
    public int createJobRole(JobRoleRequest jobRoleReq) throws SQLException, DatabaseConnectionException, IOException {
        int newJobRoleID = rolesDao.createJobRole(jobRoleReq, databaseConnector.getConnection());
        databaseConnector.closeConnection();
        return newJobRoleID;
    }
}
