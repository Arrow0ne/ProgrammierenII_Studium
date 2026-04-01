package de.thws.lektion15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MitarbeiterBefoerdernTest {

    Angestellter max;
    Abteilungsleiter artur;

    @BeforeEach
    void setup(){
        max = new Angestellter("Klaus", "Max", 1, 1000.0, "01.01.2000");
        artur = new Abteilungsleiter("Vorder", "Artur", 2, 1000.0, "20.02.1999");
    }

    @Test
    public void erstelleAngestelltenTest(){
        assertEquals("Klaus", max.nachname);
        assertEquals("Max", max.vorname);
        assertEquals(1, max.identifikator);
        assertEquals(1000.0, max.grundGehalt);
        assertEquals("01.01.2000", max.geburtsdatum);
    }
    @Test
    public void erstelleAbteilungsleiterTest(){
        assertEquals("Vorder", artur.nachname);
        assertEquals("Artur", artur.vorname);
        assertEquals(2, artur.identifikator);
        assertEquals(1000.0, artur.grundGehalt);
        assertEquals("20.02.1999", artur.geburtsdatum);
    }

    public double delta = 1e-9;

    //Angestellter
    @Test
    public void gehaltsfaktorIst1() {
        Angestellter a = new Angestellter("Müller", "Hans", 1, 3000.0, "01.01.1990");
        assertEquals(1.0, a.gehaltsfaktor);
    }

    @Test
    public void gehaltBerechnungKorrekt() {
        Angestellter a = new Angestellter("Müller", "Hans", 1, 3000.0, "01.01.1990");
        assertEquals(3000.0, a.getGehalt());
    }

    //Abteilungsleiter
    @Test
    public void gehaltsfaktorIst2() {
        Abteilungsleiter l = new Abteilungsleiter("Schmidt", "Maria", 2, 4000.0, "01.01.1980");
        assertEquals(2.0, l.gehaltsfaktor);
    }

    @Test
    public void gehaltAbteilungsleiterKorrekt() {
        Abteilungsleiter l = new Abteilungsleiter("Schmidt", "Maria", 2, 4000.0, "01.01.1980");
        assertEquals(8000.0, l.getGehalt());
    }

    //befoerdern
    @Test
    void befoerdernErhoehtFaktorUm10Prozent() {
        Abteilungsleiter l = new Abteilungsleiter("Schmidt", "Maria", 2, 4000.0, "01.01.1980");
        Angestellter a = new Angestellter("Müller", "Hans", 1, 3000.0, "01.01.1990");
        l.befoerdern(a);
        assertEquals(1.1, a.gehaltsfaktor, delta);
    }

    @Test
    void gehaltNachBefoerderungKorrekt() {
        Abteilungsleiter l = new Abteilungsleiter("Schmidt", "Maria", 2, 4000.0, "01.01.1980");
        Angestellter a = new Angestellter("Müller", "Hans", 1, 3000.0, "01.01.1990");
        l.befoerdern(a);
        assertEquals(3300.0, a.getGehalt(), delta);
    }
}
