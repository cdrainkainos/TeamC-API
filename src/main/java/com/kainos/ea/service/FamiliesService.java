package com.kainos.ea.service;

import com.kainos.ea.dao.FamilyDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.FamilyDoesNotExistException;
import com.kainos.ea.model.JobFamily;
import com.kainos.ea.model.JobRole;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class FamiliesService {

    public FamilyDao familyDao;
    public DatabaseConnection databaseConnector;

    public FamiliesService(FamilyDao familyDao, DatabaseConnection databaseConnector) {
        this.familyDao = familyDao;
        this.databaseConnector = databaseConnector;
    }

    public int updateJobFamily(JobFamily jobFamily) throws SQLException, DatabaseConnectionException, IOException {
        return familyDao.updateJobFamilyCapability(jobFamily, databaseConnector.getConnection());
    }

    public JobFamily getFamilyById(int familyId) throws SQLException, DatabaseConnectionException, IOException, FamilyDoesNotExistException {
        JobFamily jobFamily = familyDao.getFamilyById(familyId, databaseConnector.getConnection());
        if (Objects.isNull(jobFamily)){
            throw new FamilyDoesNotExistException();
        }
        return jobFamily;
    }

    public List<JobFamily> getAllFamilies() throws SQLException, DatabaseConnectionException, IOException {
        return familyDao.getAllFamilies(databaseConnector.getConnection());
    }





}
