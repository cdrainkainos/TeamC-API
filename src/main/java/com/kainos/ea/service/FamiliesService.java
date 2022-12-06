package com.kainos.ea.service;

import com.kainos.ea.dao.FamilyDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobFamily;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FamiliesService {

    public FamilyDao familyDao;
    public DatabaseConnection databaseConnector;

    public FamiliesService(FamilyDao familyDao, DatabaseConnection databaseConnector) {
        this.familyDao = familyDao;
        this.databaseConnector = databaseConnector;
    }

    public List<JobFamily> getAllFamilies() throws SQLException, DatabaseConnectionException, IOException {
        return familyDao.getAllFamilies(databaseConnector.getConnection());
    }
}
