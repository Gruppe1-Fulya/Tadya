package com.example.tadya.repository;

import com.example.tadya.model.Bericht;

import java.util.List;

public interface BerichtRepository {

    List<Bericht> findAll();
    void setBericht(Bericht bericht, int osm_id, int auto_id);


}
