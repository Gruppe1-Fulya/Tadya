package com.example.tadya.repository;

import com.example.tadya.model.Autorisierte;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorisierteRepository {
    Autorisierte getAutorisierte(Autorisierte auto);

    Autorisierte getCurrent();
    void setCurrent(Autorisierte current_auto);
    void deleteCurrent();

    Autorisierte getAutoWithId(Integer osm_id);
}
