package com.example.tadya;

import java.time.LocalDateTime;

public class Bericht {
    enum Zustand { // Levels of damage
        L0, // Intact
        L1, // Slightly damaged
        L2, // Severely damaged
        L3 // Collapsed
    }
    private String bericht_id;
    private int tote;
    private int verletzte;
    private Zustand zustand;
    private LocalDateTime bericht_zeit;
}
