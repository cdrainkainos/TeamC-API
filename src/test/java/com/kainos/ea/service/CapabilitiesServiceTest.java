package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilityDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.BandDoesNotExistException;
import com.kainos.ea.exception.CapabilityDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Capability;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CapabilitiesServiceTest {

    CapabilityDao capabilityDao = Mockito.mock(CapabilityDao.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    CapabilitiesService capabilitiesService = new CapabilitiesService(capabilityDao, databaseConnector);

    Connection conn;

    @Test
    void getAllCapabilities_shouldReturnListOfCapabilities_whenDaoReturnsListOfCapabilities() throws SQLException, DatabaseConnectionException, IOException {
        List<Capability> testList = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityDao.getAllCapabilities(conn)).thenReturn(testList);
        List<Capability> capabilities = capabilitiesService.getAllCapabilities();
        assertEquals(capabilities, testList);
    }

    @Test
    void getAllCapabilities_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityDao.getAllCapabilities(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> capabilitiesService.getAllCapabilities());
    }

    @Test
    void getCapabilityById_shouldReturnBandWhenDaoReturnsCapability() throws SQLException, DatabaseConnectionException, IOException, CapabilityDoesNotExistException {
        int testID = 1;
        Capability testCapability = new Capability(
                1,
                "Test capability"
        );
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityDao.getCapabilityById(testID, conn)).thenReturn(testCapability);
        assertEquals(testCapability, capabilitiesService.getCapabilityById(testID));
    }

    @Test
    void getCapabilityById_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityDao.getCapabilityById(testID, conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> capabilitiesService.getCapabilityById(testID));
    }

    @Test
    void getRoleById_shouldThrowException_whenDaoReturnsNULL() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityDao.getCapabilityById(testID, conn)).thenReturn(null);
        assertThrows(CapabilityDoesNotExistException.class, () -> capabilitiesService.getCapabilityById(testID));
    }
}
