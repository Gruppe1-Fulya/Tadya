package com.example.tadya.controller;

import com.example.tadya.model.Autorisierte;
import com.example.tadya.repository.AutorisierteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class AutorisierteController {

    @Autowired
    private AutorisierteRepository repository;
    @PostMapping
    public Autorisierte validate(@Valid @RequestBody Autorisierte autorisierte) {
        System.out.println(autorisierte);
        repository.setCurrent(autorisierte);
        return repository.getAutorisierte(autorisierte);
    }
    @GetMapping("/get/{osm_id}")
    public Autorisierte getAuto(@PathVariable Integer osm_id) {
        return repository.getAutoWithId(osm_id);
    }

    @GetMapping("/get")
    public Autorisierte getCurrent() {
        return repository.getCurrent();
    }
    @GetMapping("/delete")
    public void deleteCurrent() {
        repository.deleteCurrent();
    }
}
