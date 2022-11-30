package com.kainos.ea.dao;

import com.kainos.ea.exception.CompetencyPerBandLvlNotExistException;
import com.kainos.ea.model.CompetencyPerBandLvl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetencyPerBandLvlDao {
    public CompetencyPerBandLvlDao(){
    }
    public List<CompetencyPerBandLvl> getAllCompetencyPerBandLvl(Connection c, int role_id) throws SQLException, CompetencyPerBandLvlNotExistException {

        try{
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("" +role_id );
            List<CompetencyPerBandLvl> competencyPerBandLvl = new ArrayList<>();

            while (rs.next()) {
                CompetencyPerBandLvl jobSpecification = new CompetencyPerBandLvl(
                        rs.getInt("band_level"),
                        rs.getString("band_name"),
                        rs.getString("competency_name"));
                competencyPerBandLvl.add(jobSpecification);
            }

            if(competencyPerBandLvl.isEmpty()){
                throw new CompetencyPerBandLvlNotExistException("Select record that is not empty");
            } else return competencyPerBandLvl;

        } catch (SQLException e) {
            throw new SQLException ("Select query for job specifications is incorrect");
        }
    }
}
