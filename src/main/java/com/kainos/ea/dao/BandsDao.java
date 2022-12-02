package com.kainos.ea.dao;

import com.kainos.ea.model.Band;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BandsDao {

    public BandsDao(){
    }

    public Band getBandById(int bandID, Connection c) throws SQLException {

        String query = String.format("SELECT band_level, band_name FROM band WHERE id = %d", bandID);
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        if (resultSet.next()){
            Band band = new Band(
                    bandID,
                    resultSet.getInt("band_level"),
                    resultSet.getString("band_name")
            );
            return band;
        }
        return null;
    }

    public List<Band> getAllBands(Connection c) throws SQLException {

        String query = "SELECT * FROM band";
        Statement st = c.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        List<Band> bands = new ArrayList<>();

        while (resultSet.next()) {
            bands.add(new Band(
                    resultSet.getInt("id"),
                    resultSet.getInt("band_level"),
                    resultSet.getString("band_name")
            ));
        }
        return bands;
    }
}
