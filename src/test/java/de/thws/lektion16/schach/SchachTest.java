package de.thws.lektion16.schach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchachTest {
    @Test
    void testKombiniere() {
        TurmImpl turm = new TurmImpl(4, 4);
        LaeuferImpl laeufer = new LaeuferImpl(4, 4);

        Brett brettTurm = turm.gibErlaubteFelder();
        Brett brettLaeufer = laeufer.gibErlaubteFelder();

        Brett kombiniert = brettTurm.kombiniere(brettLaeufer);

        // Turmfeld prüfen (geradeaus)
        assertTrue(kombiniert.brett[3][4]); // oben
        assertTrue(kombiniert.brett[5][3]); // unten

        // Läuferfeld prüfen (diagonal)
        assertTrue(kombiniert.brett[4][4]); // diagonal
        assertTrue(kombiniert.brett[2][2]); // diagonal
    }
}
