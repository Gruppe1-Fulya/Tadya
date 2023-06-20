package com.example.tadya.repository;

import com.example.tadya.model.Bericht;

import java.util.List;

public interface BerichtRepository {

    List<Bericht> findAll();
    void setBericht(Bericht bericht, Integer osm_id, Integer auto_id);
    Bericht getBericht(Integer osm_id);

}
