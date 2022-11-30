package com.kainos.ea.service;

import com.kainos.ea.dao.CompetencyPerBandLvlDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.CompetencyPerBandLvlNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.CompetencyPerBandLvl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CompetencyService {
    public CompetencyPerBandLvlDao competencyDao;
    public DatabaseConnection databaseConnector;

    public CompetencyService(CompetencyPerBandLvlDao competencyDao, DatabaseConnection databaseConnector) {
        this.competencyDao = competencyDao;
        this.databaseConnector = databaseConnector;
    }

    public List<CompetencyPerBandLvl> getAllCompetencyPerBandLvl(int role_id) throws SQLException, DatabaseConnectionException, IOException, CompetencyPerBandLvlNotExistException {
        return competencyDao.getAllCompetencyPerBandLvl(databaseConnector.getConnection(),role_id);
    }

}
