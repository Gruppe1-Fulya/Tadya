package com.example.tadya.model;

import java.time.LocalDateTime;

public record Bericht(
        int bericht_id,
        int tote,
        int verletzte,
        DamageLevel zustand,
        LocalDateTime bericht_zeit
) {
}
