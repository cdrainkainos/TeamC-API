package com.kainos.ea.service;

import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.exception.DatabaseConnectionException;

import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobRoleXL;
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

    @Test
    void getRoleById_shouldReturnJobRoleXLWhenDaoReturnsJobRoleXL() throws SQLException, DatabaseConnectionException, IOException, JobRoleDoesNotExistException {
        int testID = 1;
        JobRoleXL testJobRole = new JobRoleXL(
                1,
                2,
                3,
                "Test role title",
                "Test job specification",
                "Test job spec link"
        );
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getRoleById(testID, conn)).thenReturn(testJobRole);
        assertEquals(testJobRole, rolesService.getRoleById(testID));
    }

    @Test
    void getRoleById_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getRoleById(testID, conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> rolesService.getRoleById(testID));
    }

    @Test
    void getRoleById_shouldThrowException_whenDaoReturnsNULL() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getRoleById(testID, conn)).thenReturn(null);
        assertThrows(JobRoleDoesNotExistException.class, () -> rolesService.getRoleById(testID));
    }

}