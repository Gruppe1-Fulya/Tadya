package com.example.tadya.model;

public record Autorisierte(
        String auto_id,
        String password,
        Einrichtung einrichtung,
        String nummer
) {
}
