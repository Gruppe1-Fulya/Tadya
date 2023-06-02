package com.example.tadya.repository;

import com.example.tadya.model.Gebeaude;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface GebeaudeRepository {
    void save(Gebeaude building);


    //int update(Gebeaude building);

    Gebeaude findById(Integer id);

    int deleteById(Integer id);

    List<Gebeaude> findAll();

    int deleteAll();
}
