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
    public void sendBericht(@Valid @RequestBody Bericht bericht, @PathVariable int osm_id, @PathVariable int auto_id) {
        repository.setBericht(bericht, osm_id, auto_id);
    }
}
