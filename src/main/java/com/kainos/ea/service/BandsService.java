package com.kainos.ea.service;

import com.kainos.ea.dao.BandsDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BandsService {

    public BandsDao bandsDao;
    public DatabaseConnection databaseConnector;

    public BandsService(BandsDao bandsDao, DatabaseConnection databaseConnector) {
        this.bandsDao = bandsDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Band> getAllBands() throws SQLException, DatabaseConnectionException, IOException {
        List allBands = bandsDao.getAllBands(databaseConnector.getConnection());
        databaseConnector.closeConnection();
        return allBands;
    }
}
