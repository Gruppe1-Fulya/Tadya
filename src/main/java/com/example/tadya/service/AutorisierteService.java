package com.example.tadya.service;

import com.example.tadya.model.*;
import com.example.tadya.repository.AutorisierteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class AutorisierteService implements AutorisierteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Autorisierte mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Autorisierte(rs.getInt("auto_id"),
                rs.getString("pass"),
                Einrichtung.valueOf(rs.getString("einrichtung")),
                rs.getString("nummer"));
    }
    @Override
    public Autorisierte getAutorisierte(Autorisierte auto) {
        try {
            Autorisierte autorisierte = jdbcTemplate.queryForObject("SELECT * FROM autorisierte WHERE auto_id=? AND pass=?",
                    AutorisierteService::mapRow, auto.auto_id(), auto.password());
            return autorisierte;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return new Autorisierte(1, null, null, null);
        }
    }
}
