package com.kainos.ea.service;

import com.kainos.ea.dao.SpecificationsDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobSpecification;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SpecificationsService {
    public SpecificationsDao specificationsDao;
    public DatabaseConnection databaseConnector;

    public SpecificationsService(SpecificationsDao specificationsDao, DatabaseConnection databaseConnector){
        this.specificationsDao = specificationsDao;
        this.databaseConnector = databaseConnector;

    }

    public List<JobSpecification> getAllSpecifications(int role_id) throws SQLException, DatabaseConnectionException, IOException {
        return specificationsDao.getAllSpecification(databaseConnector.getConnection(), role_id);
    }
}
