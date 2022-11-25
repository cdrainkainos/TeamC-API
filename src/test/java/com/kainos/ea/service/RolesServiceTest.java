package com.kainos.ea.service;

import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.exception.DatabaseConnectionException;

import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.model.JobRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RolesServiceTest {

    RolesDao rolesDao = Mockito.mock(RolesDao.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    RolesService rolesService = new RolesService(rolesDao, databaseConnector);

    Connection conn;

    @Test
    void getAllRoles_shouldReturnListOfRoles_whenDaoReturnsListOfRoles() throws SQLException, DatabaseConnectionException, IOException {
        List<JobRole> role_list = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllRoles(conn)).thenReturn(role_list);
        List<JobRole> roles = rolesService.getAllRoles();
        assertEquals(roles, role_list);
    }

    @Test
    void getAllRoles_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllRoles(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> rolesService.getAllRoles());
    }
















}