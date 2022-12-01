package com.kainos.ea.service;

import com.kainos.ea.dao.CompetencyPerBandLvlDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.CompetencyPerBandLvlNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.RoleNotExistException;
import com.kainos.ea.model.CompetencyPerBandLvl;
import com.kainos.ea.model.JobRole;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompetenciesPerBandLvlService {
    CompetencyPerBandLvlDao competencyPerBandLvlDao = Mockito.mock(CompetencyPerBandLvlDao.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);
    CompetencyService competencyService = new CompetencyService(competencyPerBandLvlDao, databaseConnector);
    Connection conn;

    @Test
    void getCompetenciesPerBandLvl_shouldReturnListOfCompetencies_whenDaoReturnsListOfCompetencies() throws SQLException, DatabaseConnectionException, IOException, CompetencyPerBandLvlNotExistException {
        int role_id = 1;
        ArrayList<CompetencyPerBandLvl> competencies_list = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyPerBandLvlDao.getAllCompetencyPerBandLvl(conn, role_id)).thenReturn(competencies_list);
        List<CompetencyPerBandLvl> competencies = competencyService.getAllCompetencyPerBandLvl(role_id);
        assertEquals(competencies, competencies_list);
    }

    @Test
    void getCompetenciesPerBandLvl_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException, CompetencyPerBandLvlNotExistException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyPerBandLvlDao.getAllCompetencyPerBandLvl(conn, 1)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> competencyService.getAllCompetencyPerBandLvl(1));
    }

    @Test
    void getCompetenciesPerBandLvl_shouldThrowCompetencyPerBandLvlNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException, IOException, CompetencyPerBandLvlNotExistException {
        int role_id = -1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(competencyPerBandLvlDao.getAllCompetencyPerBandLvl(conn, role_id)).thenThrow(CompetencyPerBandLvlNotExistException.class);
        assertThrows(CompetencyPerBandLvlNotExistException.class, () -> competencyPerBandLvlDao.getAllCompetencyPerBandLvl(conn, role_id));
    }
}
