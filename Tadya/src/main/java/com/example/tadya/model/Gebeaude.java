package com.example.tadya.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public record Gebeaude(
        @Id
        @NotNull
        Integer osm_id,
        String fclass,
        LocalDateTime mel_zeit,
        boolean besteatigung,
        Integer bericht_id,
        Integer auto_id
) {
}
