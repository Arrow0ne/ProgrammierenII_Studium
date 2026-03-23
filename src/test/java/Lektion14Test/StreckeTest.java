package Lektion14Test;

import Lektion14.Strecke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StreckeTest {
    @Test
    public void testMinInA(){
        Strecke test = new Strecke(1,2);
        assertEquals(1,test.A);
        assertEquals(2,test.B);
        try{
            test = new Strecke(-1, 2);
            fail("IllegalArgumentException erwartet");
        }catch(IllegalArgumentException e){
            String fehler = e.getMessage();
            assertEquals("Nur ganzzahlige positive Zahlen", fehler);
        }
        test = new Strecke(2,1);
        assertTrue(test.A < test.B);
    }
    @Test
    public void testUeberschneidung(){
        Strecke x;
        Strecke y;

        // nur Berührung
        x = new Strecke(1, 3);
        y = new Strecke(3, 5);
        assertFalse(x.ueberschneidung(y));

        // komplett getrennt
        x = new Strecke(1, 2);
        y = new Strecke(4, 6);
        assertFalse(x.ueberschneidung(y));

        // teilweise Überschneidung
        x = new Strecke(1, 4);
        y = new Strecke(3, 6);
        assertTrue(x.ueberschneidung(y));

        // andere Reihenfolge, auch Überschneidung
        x = new Strecke(3, 6);
        y = new Strecke(1, 4);
        assertTrue(x.ueberschneidung(y));

        // eine Strecke liegt komplett in der anderen
        x = new Strecke(1, 10);
        y = new Strecke(4, 6);
        assertTrue(x.ueberschneidung(y));

        // identische Strecken
        x = new Strecke(2, 5);
        y = new Strecke(2, 5);
        assertTrue(x.ueberschneidung(y));
    }
    @Test
    public void testToString() {
        Strecke test;

        test = new Strecke(4, 4);
        assertEquals("4", test.toString());

        test = new Strecke(3, 5);
        assertEquals("3--5", test.toString());

        test = new Strecke(2, 8);
        assertEquals("2------8", test.toString());

        test = new Strecke(5, 3);
        assertEquals("3--5", test.toString());
    }
}