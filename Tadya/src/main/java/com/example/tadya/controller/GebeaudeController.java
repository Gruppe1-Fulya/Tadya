package com.example.tadya.controller;

import com.example.tadya.model.Bericht;
import com.example.tadya.model.Gebeaude;
import com.example.tadya.repository.BerichtRepository;
import com.example.tadya.repository.GebeaudeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/map")
@CrossOrigin
public class GebeaudeController {
    @Autowired
    private GebeaudeRepository repository;

    @GetMapping
    public List<Gebeaude> findAll() {
        return repository.findAll();
    }
    @GetMapping("/get/{osm_id}")
    public Gebeaude getBuilding(@PathVariable Integer osm_id) {
        return repository.findById(osm_id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void sendHelpRequest(@Valid @RequestBody Gebeaude gebeaude) {
        repository.save(gebeaude);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{osm_id}")
    public void deleteBuilding(@PathVariable Integer osm_id) {
        repository.deleteById(osm_id);
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        repository.deleteAll();
    }
}
