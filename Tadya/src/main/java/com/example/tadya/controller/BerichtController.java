package com.example.tadya.controller;

import com.example.tadya.model.Bericht;
import com.example.tadya.repository.BerichtRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
@CrossOrigin
public class BerichtController {
    @Autowired
    private BerichtRepository repository;

    @PostMapping("/bericht/{osm_id}/{auto_id}")
    public void sendBericht(@Valid @RequestBody Bericht bericht, @PathVariable Integer osm_id, @PathVariable Integer auto_id) {
        repository.setBericht(bericht, osm_id, auto_id);
    }
    @GetMapping("/bericht/get/{osm_id}")
    public Bericht getBericht(@PathVariable Integer osm_id) {
        return repository.getBericht(osm_id);
    }
}
