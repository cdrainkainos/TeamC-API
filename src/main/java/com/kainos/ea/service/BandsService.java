package com.kainos.ea.service;

import com.kainos.ea.dao.BandsDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.Band;
import com.kainos.ea.model.JobRoleXL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class BandsService {

    public BandsDao bandsDao;
    public DatabaseConnection databaseConnector;

    public BandsService(BandsDao bandsDao, DatabaseConnection databaseConnector) {
        this.bandsDao = bandsDao;
        this.databaseConnector = databaseConnector;
    }

    public Band getBandById(int bandID) throws SQLException, DatabaseConnectionException, IOException, BandDoesNotExistException {
        Band band = bandsDao.getBandById(bandID, databaseConnector.getConnection());
        if (Objects.isNull(band)){
            throw new BandDoesNotExistException();
        }
        return band;
    }

    public List<Band> getAllBands() throws SQLException, DatabaseConnectionException, IOException {
        return bandsDao.getAllBands(databaseConnector.getConnection());
    }
}
