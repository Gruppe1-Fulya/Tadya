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
public class GebeaudeCollectionRepository {
    private final List<Gebeaude> gebeaudeList  = new ArrayList<>();
    public GebeaudeCollectionRepository() {

    }

    public List<Gebeaude> findAll() {
        return gebeaudeList;
    }

    public Optional<Gebeaude> findById(Integer osm_id) {
        return gebeaudeList.stream().filter(gebeaude -> gebeaude.osm_id().equals(osm_id)).findFirst();
    }

    public void save(Gebeaude building) {
        gebeaudeList.add(building);
    }
    @PostConstruct
    private void init() {


    }
}
