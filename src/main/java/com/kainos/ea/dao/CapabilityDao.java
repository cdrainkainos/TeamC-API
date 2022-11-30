package com.kainos.ea.dao;
import com.kainos.ea.model.Capability;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDao {

    public CapabilityDao(){
    }

    public Capability getCapabilityById(int capabilityID, Connection c) throws SQLException {

        String query = String.format("SELECT capability_name FROM capability WHERE id = %d", capabilityID);
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        if (resultSet.next()){
            Capability capability = new Capability(
                    capabilityID,
                    resultSet.getString("capability_name")
            );
            return capability;
        }
        return null;
    }

    public List<Capability> getAllCapabilities(Connection c) throws SQLException {

        String query = "SELECT * FROM capability";
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        List<Capability> capabilities = new ArrayList<>();

        while (resultSet.next()) {
            capabilities.add(new Capability(
                    resultSet.getInt("id"),
                    resultSet.getString("capability_name")
            ));
        }
        return capabilities;
    }
}
