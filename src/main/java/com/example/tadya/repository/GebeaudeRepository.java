package com.example.tadya.repository;

import com.example.tadya.model.Gebeaude;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GebeaudeRepository {
    private final List<Gebeaude> gebeaudeList  = new ArrayList<>();
    public GebeaudeRepository() {

    }

    public List<Gebeaude> findAll() {
        return gebeaudeList;
    }

    public Optional<Gebeaude> findById(String osm_id) {
        return gebeaudeList.stream().filter(gebeaude -> gebeaude.osm_id().equals(osm_id)).findFirst();
    }

    public void addBuilding(Gebeaude building) {
        gebeaudeList.add(building);
    }
    @PostConstruct
    private void init() {
        Gebeaude g = new Gebeaude(
                "12345", "building",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                false,
                null,
                null
        );
        Gebeaude t = new Gebeaude(
                "123456", "building",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                false,
                null,
                null
        );
        gebeaudeList.add(g);
        gebeaudeList.add(t);
    }
}
