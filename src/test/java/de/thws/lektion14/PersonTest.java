package de.thws.lektion14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void testCreatePerson() {
        Person test1 = new Person("Artur", "Wacker", "Musterstrasse", "12a", 97424, "Schweinfurt");
        assertEquals("Artur", test1.vorname);
        assertEquals("Wacker", test1.nachname);
        assertEquals("Musterstrasse", test1.adresse.strasse);
        assertEquals("12a", test1.adresse.hausnummer);
        assertEquals(97424, test1.adresse.postleitzahl);
        assertEquals("Schweinfurt", test1.adresse.ort);

        try {
            test1 = new Person("artur", "Wacker", "Musterstrasse", "12a", 97424, "Schweinfurt");
            fail("IllegalArgumentException ewartet");
        } catch (IllegalArgumentException e) {
            String fehler = e.getMessage();
            assertEquals("Vorname muss mit einem Großbuchstaben beginnen", fehler);
        }
        try {
            test1 = new Person("Artur", "Wacker", "musterstrasse", "12a", 97424, "Schweinfurt");
        } catch (IllegalArgumentException e) {
            String fehler = e.getMessage();
            assertEquals("Strasse muss mit einem Grossbuchstaben anfangen", fehler);
        }
        try {
            test1 = new Person("Artur", "Wacker", "Musterstrasse", "b12a", 97424, "Schweinfurt");
        } catch (IllegalArgumentException e) {
            String fehler = e.getMessage();
            assertEquals("Hausnummer muss mit einer Ziffer beginnen", fehler);
        }
        try {
            test1 = new Person("Artur", "Wacker", "Musterstrasse", "12a", 97424, "schweinfurt");
        } catch (IllegalArgumentException e) {
            String fehler = e.getMessage();
            assertEquals("Ort muss mit einem Grossbuchstaben anfangen", fehler);
        }
    }
}
