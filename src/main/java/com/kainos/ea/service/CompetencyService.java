package com.kainos.ea.service;

import com.kainos.ea.dao.CompetencyDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.CompetencyPerBandLvlNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.BandCompetencies;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CompetencyService {
    public CompetencyDao competencyDao;
    public DatabaseConnection databaseConnector;

    public CompetencyService(CompetencyDao competencyDao, DatabaseConnection databaseConnector) {
        this.competencyDao = competencyDao;
        this.databaseConnector = databaseConnector;
    }

    public List<BandCompetencies> getAllCompetencyPerBandLvl(int role_id) throws SQLException, DatabaseConnectionException, IOException, CompetencyPerBandLvlNotExistException {
        return competencyDao.getAllCompetencyPerBandLvl(databaseConnector.getConnection(),role_id);
    }
}
