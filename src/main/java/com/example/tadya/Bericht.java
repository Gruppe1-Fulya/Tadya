package com.example.tadya;

import java.time.LocalDateTime;

public class Bericht {
    enum Zustand {
        yikilmis,
        orta,
        saglam
    }
    private String bericht_id;
    private int tote;
    private int verletzte;
    private Zustand zustand;
    private LocalDateTime bericht_zeit;
}
