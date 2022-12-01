package com.kainos.ea.service;

import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleXL;

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

    public int updateJobRole(JobRoleXL jobRoleXL) throws SQLException, DatabaseConnectionException, IOException {
        return rolesDao.updateJobRole(jobRoleXL, databaseConnector.getConnection());
    }

    public List<JobRole> getAllRoles() throws SQLException, DatabaseConnectionException, IOException {
        return rolesDao.getAllRoles(databaseConnector.getConnection());
    }

    public JobRoleXL getRoleById(int roleID) throws DatabaseConnectionException, SQLException, IOException, JobRoleDoesNotExistException {
        JobRoleXL jobRoleXL = rolesDao.getRoleById(roleID, databaseConnector.getConnection());
        if (Objects.isNull(jobRoleXL)){
            throw new JobRoleDoesNotExistException();
        }
        return jobRoleXL;
    }
}
