package com.example.tadya.model;

public record Autorisierte(
        int auto_id,
        String password,
        Einrichtung einrichtung,
        String nummer
) {
}
