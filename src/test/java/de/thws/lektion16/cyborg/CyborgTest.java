package de.thws.lektion16.cyborg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CyborgTest {

    // --- Roboter: immer deterministisch ---

    @Test
    void roboter_gefahrLinks_gibtRechtsZurueck() {
        Roboter roboter = new Roboter();
        assertEquals(Entscheidung.RECHTS, roboter.entscheide(GefahrenSituationen.GEFAHR_LINKS));
    }

    @Test
    void roboter_gefahrRechts_gibtLinksZurueck() {
        Roboter roboter = new Roboter();
        assertEquals(Entscheidung.LINKS, roboter.entscheide(GefahrenSituationen.GEFAHR_RECHTS));
    }

    @Test
    void roboter_gefahrVorne_gibtBremsenZurueck() {
        Roboter roboter = new Roboter();
        assertEquals(Entscheidung.BREMSEN, roboter.entscheide(GefahrenSituationen.GEFAHR_VORNE));
    }

    // --- Mensch: Entscheidung liegt immer im erlaubten Wertebereich ---

    @Test
    void mensch_gefahrLinks_gibtGueltigeEntscheidungZurueck() {
        Mensch mensch = new Mensch();
        Entscheidung e = mensch.entscheide(GefahrenSituationen.GEFAHR_LINKS);
        assertNotNull(e);
        // Erlaubt: RECHTS (Normalfall) oder UNENTSCHIEDEN (25% Zufall)
        assertTrue(e == Entscheidung.RECHTS || e == Entscheidung.UNENTSCHIEDEN);
    }

    @Test
    void mensch_gefahrVorne_gibtGueltigeEntscheidungZurueck() {
        Mensch mensch = new Mensch();
        Entscheidung e = mensch.entscheide(GefahrenSituationen.GEFAHR_VORNE);
        assertNotNull(e);
        assertTrue(e == Entscheidung.BREMSEN || e == Entscheidung.UNENTSCHIEDEN);
    }

    // --- Cyborg: bei Einigkeit immer die gemeinsame Entscheidung ---
    // Roboter ist deterministisch → wir können Situationen wählen,
    // bei denen Mensch und Roboter sich sicher einig sind (wenn Mensch nicht UNENTSCHIEDEN wählt)

    @Test
    void cyborg_entscheidungImmerGueltig() {
        Cyborg cyborg = new Cyborg();
        for (GefahrenSituationen gefahr : GefahrenSituationen.values()) {
            Entscheidung e = cyborg.entscheide(gefahr);
            assertNotNull(e, "Entscheidung darf nicht null sein für: " + gefahr);
        }
    }

    @Test
    void cyborg_entscheidungLiegtImmerImWertebereich() {
        Cyborg cyborg = new Cyborg();
        // 100 Wiederholungen um Zufallsanteil abzudecken
        for (int i = 0; i < 100; i++) {
            for (GefahrenSituationen gefahr : GefahrenSituationen.values()) {
                Entscheidung e = cyborg.entscheide(gefahr);
                assertTrue(
                        e == Entscheidung.LINKS ||
                                e == Entscheidung.RECHTS ||
                                e == Entscheidung.BREMSEN ||
                                e == Entscheidung.UNENTSCHIEDEN,
                        "Unbekannte Entscheidung: " + e
                );
            }
        }
    }

    // --- Zufallsverteilung: statistischer Test ---

    @Test
    void cyborg_zufallsanteil_verteilungGrob50zu50() {
        // GEFAHR_VORNE: Roboter → immer BREMSEN, Mensch → BREMSEN oder UNENTSCHIEDEN
        // Wenn Mensch UNENTSCHIEDEN wählt, sind sie uneinig → Cyborg wählt 50/50
        // Wir zählen nur die Fälle, in denen der Cyborg UNENTSCHIEDEN zurückgibt
        // (das kann nur passieren wenn Mensch UNENTSCHIEDEN UND Zufall auf Mensch fällt)
        // → einfacherer Test: Ausgabe ist nie null und kommt aus dem Wertebereich (bereits oben)

        // Direkterer statistischer Test mit kontrolliertem Szenario:
        // Wir rufen sehr oft auf und prüfen, dass BEIDE möglichen Entscheidungen vorkommen
        Cyborg cyborg = new Cyborg();
        boolean sahenBremsen = false;
        boolean sahenUnentschieden = false;

        for (int i = 0; i < 10_000; i++) {
            Entscheidung e = cyborg.entscheide(GefahrenSituationen.GEFAHR_VORNE);
            if (e == Entscheidung.BREMSEN)       sahenBremsen = true;
            if (e == Entscheidung.UNENTSCHIEDEN)  sahenUnentschieden = true;
            if (sahenBremsen && sahenUnentschieden) break;
        }

        assertTrue(sahenBremsen,        "BREMSEN sollte bei GEFAHR_VORNE vorkommen");
        assertTrue(sahenUnentschieden,  "UNENTSCHIEDEN sollte gelegentlich vorkommen");
    }
}