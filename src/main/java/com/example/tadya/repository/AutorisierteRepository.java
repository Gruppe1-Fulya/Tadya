package com.example.tadya.repository;

import com.example.tadya.model.Autorisierte;

public interface AutorisierteRepository {
    Autorisierte getAutorisierte(Autorisierte auto);

    Autorisierte getCurrent();
    void setCurrent(Autorisierte current_auto);
}
