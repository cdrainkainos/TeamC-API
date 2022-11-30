package com.kainos.ea.service;

import com.kainos.ea.dao.CapabilityDao;
import com.kainos.ea.database.DatabaseConnection;
import com.kainos.ea.exception.CapabilityDoesNotExistException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.Capability;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class CapabilitiesService {

    public CapabilityDao capabilityDao;
    public DatabaseConnection databaseConnector;

    public CapabilitiesService(CapabilityDao capabilityDao, DatabaseConnection databaseConnector) {
        this.capabilityDao = capabilityDao;
        this.databaseConnector = databaseConnector;
    }

    public Capability getCapabilityById(int capabilityID) throws SQLException, DatabaseConnectionException, IOException, CapabilityDoesNotExistException {
        Capability capability = capabilityDao.getCapabilityById(capabilityID, databaseConnector.getConnection());
        if (Objects.isNull(capability)){
            throw new CapabilityDoesNotExistException();
        }
        return capability;
    }

    public List<Capability> getAllCapabilities() throws SQLException, DatabaseConnectionException, IOException {
        return capabilityDao.getAllCapabilities(databaseConnector.getConnection());
    }
}
