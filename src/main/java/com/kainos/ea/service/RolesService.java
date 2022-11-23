package com.kainos.ea.service;

import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.model.Job_Role;

import java.sql.SQLException;
import java.util.List;

public class RolesService {
    public RolesDao rolesDao;
    public DatabaseConnection databaseConnector;

    public RolesService(RolesDao rolesDao, DatabaseConnection databaseConnector){
        this.rolesDao = rolesDao;
        this.databaseConnector = databaseConnector;

    }

    public List<Job_Role> getAllRoles() throws SQLException {
        return rolesDao.getAllRoles(databaseConnector.getConnection());
    }
}
