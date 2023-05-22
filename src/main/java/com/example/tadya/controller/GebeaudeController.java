package com.example.tadya.controller;

import com.example.tadya.model.Gebeaude;
import com.example.tadya.repository.GebeaudeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/map")
public class GebeaudeController {

    private final GebeaudeRepository repository;

    public GebeaudeController(GebeaudeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Gebeaude> findAll() {
        return repository.findAll();
    }
    @GetMapping("/{osm_id}")
    public Gebeaude findById(@PathVariable String osm_id) {
        return repository.findById(osm_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bina bukunamadi"));
    }

}
