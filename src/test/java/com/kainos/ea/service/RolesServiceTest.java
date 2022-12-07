package com.kainos.ea.service;

import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.JobRoleDoesNotExistException;
import com.kainos.ea.model.JobRoleResponse;
import com.kainos.ea.model.JobRoleRequest;
import com.kainos.ea.exception.RoleNotExistException;
import com.kainos.ea.model.JobSpecification;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RolesServiceTest {

    RolesDao rolesDao = Mockito.mock(RolesDao.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    RolesService rolesService = new RolesService(rolesDao, databaseConnector);

    Connection conn;

    @Test
    void getAllRoles_shouldReturnListOfRoles_whenDaoReturnsListOfRoles() throws SQLException, DatabaseConnectionException, IOException {
        ArrayList<JobRoleResponse> role_list = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllRoles(conn)).thenReturn(role_list);
        List<JobRoleResponse> roles = rolesService.getAllRoles();
        assertEquals(roles, role_list);
        databaseConnector.closeConnection();
    }

    @Test
    void getAllRoles_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllRoles(conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> rolesService.getAllRoles());
        databaseConnector.closeConnection();
    }

    @Test
    void getRoleById_shouldReturnJobRoleRequestWhenDaoReturnsJobRoleRequest() throws SQLException, DatabaseConnectionException, IOException, JobRoleDoesNotExistException {
        int testID = 1;
        JobRoleRequest testJobRole = new JobRoleRequest(
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
        databaseConnector.closeConnection();
    }

    @Test
    void getRoleById_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getRoleById(testID, conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> rolesService.getRoleById(testID));
        databaseConnector.closeConnection();
    }

    @Test
    void getRoleById_shouldThrowException_whenDaoReturnsNULL() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getRoleById(testID, conn)).thenReturn(null);
        assertThrows(JobRoleDoesNotExistException.class, () -> rolesService.getRoleById(testID));
        databaseConnector.closeConnection();
    }

    @Test
    void getAllSpecifications_shouldReturnSpecification_whenDaoReturnsSpecification() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int role_id = 1;
        JobSpecification specification_list = new JobSpecification("test", "test", "test");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllSpecification(conn, role_id)).thenReturn(specification_list);
        JobSpecification specifications = rolesService.getAllSpecifications(role_id);
        assertEquals(specifications, specification_list);
        databaseConnector.closeConnection();
    }

    @Test
    void getAllSpecifications_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int role_id = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllSpecification(conn, role_id)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> rolesService.getAllSpecifications(role_id));
        databaseConnector.closeConnection();
    }

    @Test
    void getAllSpecifications_shouldThrowUserDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int role_id = -1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllSpecification(conn, role_id)).thenThrow(RoleNotExistException.class);
        assertThrows(RoleNotExistException.class, () -> rolesService.getAllSpecifications(role_id));
        databaseConnector.closeConnection();
    }

    @Test
    void updateJobRole_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        JobRoleRequest testJobRole = new JobRoleRequest(
                1,
                2,
                3,
                "Test role title",
                "Test job specification",
                "Test job spec link"
        );
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.updateJobRole(testID, testJobRole, conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, ()-> rolesService.updateJobRole(testID, testJobRole));
        databaseConnector.closeConnection();
    }

    @Test
    void updateJobRole_shouldReturnTrue_whenDaoReturnsTrue() throws SQLException, DatabaseConnectionException, IOException {
        int testID = 1;
        JobRoleRequest testJobRole = new JobRoleRequest(
                1,
                2,
                3,
                "Test role title",
                "Test job specification",
                "http://www.test.com"
        );
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.updateJobRole(testID, testJobRole, conn)).thenReturn(true);
        boolean result = rolesService.updateJobRole(testID, testJobRole);
        assertTrue(result);
        databaseConnector.closeConnection();
    }
}
