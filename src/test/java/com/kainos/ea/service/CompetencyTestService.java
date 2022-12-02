package com.kainos.ea.service;

import com.kainos.ea.dao.CompetencyDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.BandNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.BandCompetencies;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompetencyTestService {
    CompetencyDao competencyDao = Mockito.mock(CompetencyDao.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);
    CompetencyService competencyService = new CompetencyService(competencyDao, databaseConnector);
    Connection conn;

    @Test
    void getCompetenciesPerBandLvl_shouldReturnCompetencies_whenDaoReturnsCompetencies() throws SQLException, DatabaseConnectionException, IOException, BandNotExistException {
        int band_id = 1;
        List<String > competencies_list = new ArrayList<>();
        BandCompetencies bandCompetencies = new BandCompetencies(1, "test", competencies_list);
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyDao.getAllCompetencyPerBandLvl(conn, band_id)).thenReturn(bandCompetencies);
        BandCompetencies competencies = competencyService.getAllCompetencyPerBandLvl(band_id);
        assertEquals(competencies, bandCompetencies);
    }

    @Test
    void getCompetenciesPerBandLvl_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException, BandNotExistException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyDao.getAllCompetencyPerBandLvl(conn, 1)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> competencyService.getAllCompetencyPerBandLvl(1));
    }

    @Test
    void getCompetenciesPerBandLvl_shouldThrowCompetencyPerBandLvlNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException, IOException, BandNotExistException {
        int band_id = -1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyDao.getAllCompetencyPerBandLvl(conn, band_id)).thenThrow(BandNotExistException.class);
        assertThrows(BandNotExistException.class, () -> competencyDao.getAllCompetencyPerBandLvl(conn, band_id));
    }


}
