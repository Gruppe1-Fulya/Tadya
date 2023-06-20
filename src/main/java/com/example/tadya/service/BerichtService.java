package com.example.tadya.service;

import com.example.tadya.model.Autorisierte;
import com.example.tadya.model.Bericht;
import com.example.tadya.model.DamageLevel;
import com.example.tadya.model.Gebeaude;
import com.example.tadya.repository.BerichtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class BerichtService implements BerichtRepository {
    private Bericht current;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Bericht mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Bericht(rs.getInt("bericht_id"),
                rs.getInt("tote"),
                rs.getInt("verletzte"),
                DamageLevel.valueOf(rs.getString("zustand")),
                rs.getObject("bericht_zeit", LocalDateTime.class));
    }
    @Override
    public List<Bericht> findAll() {
        return jdbcTemplate.query("SELECT * from bericht", BerichtService::mapRow);
    }
    @Override
    public void setBericht(Bericht bericht, Integer osm_id, Integer auto_id) {
        System.out.println(bericht);
        jdbcTemplate.update("INSERT INTO bericht (bericht_id, bericht_zeit, tote, verletzte, zustand) VALUES(?,?,?,?,?)", new Object[]
                {bericht.bericht_id(), bericht.bericht_zeit(), bericht.tote(), bericht.verletzte(), bericht.zustand().name()});
        jdbcTemplate.update("UPDATE gebeaude SET bericht_id = ?, besteatigung = ?, auto_id = ? WHERE osm_id=?", new Object[]
                {bericht.bericht_id(), true, auto_id, osm_id});
    }
    @Override
    public Bericht getBericht(Integer osm_id) {
        try{
            Bericht bericht = jdbcTemplate.queryForObject("SELECT * FROM bericht WHERE bericht.bericht_id = (SELECT gebeaude.bericht_id FROM gebeaude WHERE gebeaude.osm_id=?)",
                    BerichtService::mapRow, osm_id);
            return bericht;
        }
        catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return new Bericht(0,0 ,0, null, null);
        }
    }
}
