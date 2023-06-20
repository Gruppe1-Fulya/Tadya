package com.example.tadya.model;

public record Autorisierte(
        int auto_id,
        String auto_name,
        String password,
        Einrichtung einrichtung,
        String nummer
) {
}
