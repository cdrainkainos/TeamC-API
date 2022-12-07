package com.kainos.ea.service;

import com.kainos.ea.dao.BandsDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Band;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandsServiceTest {
    BandsDao bandsDao = Mockito.mock(BandsDao.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);
    BandsService bandsService = new BandsService(bandsDao, databaseConnector);
    Connection conn;

    @Test
    void getAllBands_shouldReturnListOfBands_whenDaoReturnsListOfBands() throws SQLException, DatabaseConnectionException, IOException {
        List<Band> testList = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(bandsDao.getAllBands(conn)).thenReturn(testList);
        List<Band> bands = bandsService.getAllBands();
        assertEquals(bands, testList);
        databaseConnector.closeConnection();
    }

    @Test
    void getAllBands_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(bandsDao.getAllBands(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> bandsService.getAllBands());
        databaseConnector.closeConnection();
    }

}
