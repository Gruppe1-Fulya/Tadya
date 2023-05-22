package com.example.tadya.model;

public record Gebeaude(
        String osm_id,
        String fclass,
        String mel_zeit,
        boolean besteatigung,
        Bericht bericht,
        Autorisierte autorisierte
) {
}
