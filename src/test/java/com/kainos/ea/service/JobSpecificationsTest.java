package com.kainos.ea.service;


import com.kainos.ea.dao.RolesDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.RoleNotExistException;
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

    RolesDao rolesDao = Mockito.mock(RolesDao.class);

    DatabaseConnection databaseConnector = Mockito.mock(DatabaseConnection.class);

    RolesService rolesService = new RolesService(rolesDao, databaseConnector);
    Connection conn;

    @Test
    void getAllRoles_shouldReturnListOfSpecifications_whenDaoReturnsListOfSpecifications() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
       int role_id = 1;
        List<JobSpecification> specification_list = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllSpecification(conn, role_id)).thenReturn(specification_list);
        List<JobSpecification> specifications = rolesService.getAllSpecifications(role_id);
        assertEquals(specifications, specification_list);
    }

    @Test
    void getAllSpecifications_shouldThrowException_whenDaoThrowsException() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int role_id = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllSpecification(conn, role_id)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> rolesService.getAllSpecifications(role_id));
    }

    @Test
    void getAllSpecifications_shouldThrowUserDoesNotExistException_whenDaoReturnsNull() throws SQLException, DatabaseConnectionException, IOException, RoleNotExistException {
        int role_id = -1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(rolesDao.getAllSpecification(conn, role_id)).thenThrow(RoleNotExistException.class);
        assertThrows(RoleNotExistException.class, () -> rolesService.getAllSpecifications(role_id));
    }
}
