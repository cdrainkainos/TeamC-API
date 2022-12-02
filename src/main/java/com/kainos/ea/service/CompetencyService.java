package com.kainos.ea.service;

import com.kainos.ea.dao.CompetencyDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.BandNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.BandCompetencies;

import java.io.IOException;
import java.sql.SQLException;

public class CompetencyService {
    public CompetencyDao competencyDao;
    public DatabaseConnection databaseConnector;

    public CompetencyService(CompetencyDao competencyDao, DatabaseConnection databaseConnector) {
        this.competencyDao = competencyDao;
        this.databaseConnector = databaseConnector;
    }

    public BandCompetencies getAllCompetencyPerBandLvl(int band_id) throws SQLException, DatabaseConnectionException, IOException, BandNotExistException {
        return competencyDao.getAllCompetencyPerBandLvl(databaseConnector.getConnection(), band_id);
    }
}
