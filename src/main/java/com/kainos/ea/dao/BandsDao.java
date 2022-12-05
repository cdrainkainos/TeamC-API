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
