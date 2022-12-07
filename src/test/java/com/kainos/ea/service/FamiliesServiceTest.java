package com.kainos.ea.service;

import com.kainos.ea.dao.FamilyDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.JobFamily;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FamiliesServiceTest {
    FamilyDao familyDao = Mockito.mock(FamilyDao.class);
    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);
    FamiliesService familiesService = new FamiliesService(familyDao, databaseConnector);
    Connection conn;

    @Test
    void getAllFamilies_shouldReturnListOfFamilies_whenDaoReturnsListOfFamilies() throws SQLException, DatabaseConnectionException, IOException {
        List<JobFamily> testList = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(familyDao.getAllFamilies(conn)).thenReturn(testList);
        List<JobFamily> families = familiesService.getAllFamilies();
        assertEquals(families, testList);
        databaseConnector.closeConnection();
    }

    @Test
    void getAllFamilies_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(familyDao.getAllFamilies(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> familiesService.getAllFamilies());
        databaseConnector.closeConnection();
    }

}
