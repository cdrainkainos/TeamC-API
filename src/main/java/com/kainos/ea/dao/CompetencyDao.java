package com.kainos.ea.dao;

import com.kainos.ea.exception.BandNotExistException;
import com.kainos.ea.model.BandCompetencies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetencyDao {
    public CompetencyDao(){
    }
    public BandCompetencies getAllCompetencyPerBandLvl(Connection c, int band_id) throws SQLException, BandNotExistException {

        try{
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT\n" +
                    "    band.id band_level, band_name, competency_name\n" +
                    "FROM\n" +
                    "    band\n" +
                    "        JOIN\n" +
                    "    competency_band ON competency_band.band_id\n" +
                    "        JOIN\n" +
                    "    competency ON competency_id = competency.id\n" +
                    "WHERE\n" +
                    "    band.id = " + band_id );
            List<String> competencyBandNames= new ArrayList<>();
            String bandName = null;
            int bandLevel = 0;
            while(rs.next()){
                bandLevel= rs.getInt("band_level");
                bandName= rs.getString("band_name");
                String competencyBandName= (rs.getString("competency_name"));
                competencyBandNames.add(competencyBandName);
            }

                BandCompetencies bandLvls = new BandCompetencies(
                        bandLevel,
                        bandName,
                        competencyBandNames);


            if(competencyBandNames.isEmpty()){
                throw new BandNotExistException("competencies not found");
            } else return bandLvls;

        } catch (SQLException e) {
            throw new SQLException ("Select query for competency per band level is incorrect");
        }
    }
}
