package com.example.tadya.model;

import java.time.LocalDateTime;

public record Bericht(
        Integer bericht_id,
        Integer tote,
        Integer verletzte,
        DamageLevel zustand,
        LocalDateTime bericht_zeit
) {
}
