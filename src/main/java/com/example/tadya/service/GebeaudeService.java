package com.example.tadya.service;

import com.example.tadya.model.Gebeaude;
import com.example.tadya.repository.GebeaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GebeaudeService implements GebeaudeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Gebeaude mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Gebeaude(rs.getInt("osm_id"),
                rs.getString("fclass"),
                rs.getObject("mel_zeit", LocalDateTime.class),
                rs.getBoolean("besteatigung"),
                rs.getInt("bericht_id"),
                rs.getInt("auto_id"));
    }

    @Override
    public void save(Gebeaude building) {
        jdbcTemplate.update("INSERT INTO gebeaude (osm_id, mel_zeit, fclass, besteatigung, bericht_id, auto_id) VALUES(?,?,?,?,?,?)", new Object[]
                {building.osm_id(), LocalDateTime.now(), building.fclass(),
                        building.besteatigung(), building.bericht_id(), building.auto_id()});
    }
    /*
    @Override
    public int update(Tutorial tutorial) {
        return jdbcTemplate.update("UPDATE tutorials SET title=?, description=?, published=? WHERE id=?",
                new Object[] { tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished(), tutorial.getId() });
    }
    */
    @Override
    public Gebeaude findById(Integer id) {
        try {
            Gebeaude gebeaude = jdbcTemplate.queryForObject("SELECT * FROM gebeaude WHERE osm_id=?",
                    GebeaudeService::mapRow, id);
            return gebeaude;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Integer osm_id) {
        return jdbcTemplate.update("DELETE FROM gebeaude WHERE osm_id=?", osm_id);
    }

    @Override
    public List<Gebeaude> findAll() {
        return jdbcTemplate.query("SELECT * from gebeaude", GebeaudeService::mapRow);
    }
    /*
    @Override
    public List<Gebeaude> findByPublished(boolean published) {
        return jdbcTemplate.query("SELECT * from tutorials WHERE published=?",
                JdbcGebeaudeRepository::mapRow, published);
    }
    */
    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from gebeaude");
    }
}
