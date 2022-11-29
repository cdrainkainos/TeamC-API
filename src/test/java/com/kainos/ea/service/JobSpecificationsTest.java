package com.kainos.ea.service;


import com.kainos.ea.dao.SpecificationsDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.EmptyListException;
import com.kainos.ea.model.JobSpecification;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JobSpecificationsTest {
    SpecificationsDao specificationsDao = Mockito.mock(SpecificationsDao.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    SpecificationsService specificationsService = new SpecificationsService(specificationsDao, databaseConnector);

    Connection conn;

    @Test
    void getAllRoles_shouldReturnListOfSpecifications_whenDaoReturnsListOfSpecifications() throws SQLException, DatabaseConnectionException, IOException, EmptyListException {
       int role_id = 1;
        List<JobSpecification> specification_list = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(specificationsDao.getAllSpecification(conn, role_id)).thenReturn(specification_list);
        List<JobSpecification> specifications = specificationsService.getAllSpecifications(role_id);
        assertEquals(specifications, specification_list);
    }

    @Test
    void getAllSpecifications_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException, EmptyListException {
        int role_id = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(specificationsDao.getAllSpecification(conn, role_id)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> specificationsService.getAllSpecifications(role_id));
    }

    @Test
    void getAllSpecifications_shouldThrowException_whenRecordIsEmpty() throws SQLException, DatabaseConnectionException, IOException, EmptyListException {
        int role_id = -1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(specificationsDao.getAllSpecification(conn, role_id)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> specificationsService.getAllSpecifications(role_id));
    }
}
