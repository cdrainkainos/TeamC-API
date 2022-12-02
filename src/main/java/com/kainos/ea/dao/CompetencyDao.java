package com.kainos.ea.dao;

import com.kainos.ea.exception.CompetencyPerBandLvlNotExistException;
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
    public List<BandCompetencies> getAllCompetencyPerBandLvl(Connection c, int role_id) throws SQLException, CompetencyPerBandLvlNotExistException {

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
                    "    band.id = " + role_id );
            List<BandCompetencies> bandCompetencies = new ArrayList<>();
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
                bandCompetencies.add(bandLvls);


            if(competencyBandNames.isEmpty()){
                throw new CompetencyPerBandLvlNotExistException("competencies not found");
            } else return bandCompetencies;

        } catch (SQLException e) {
            throw new SQLException ("Select query for competency per band level is incorrect");
        }
    }
}
