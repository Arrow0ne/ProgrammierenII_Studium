package de.thws.lektion14;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KugelvolumenTest {


        @Test
        public void testBerechneKugelvolumen(){
            final double delta = 0.000001;
            assertEquals(0.0, Kugelvolumen.berechneKugelvolumen(0), delta);
            assertEquals(3.141592653589793,Kugelvolumen.berechneKugelvolumen(1), delta);
            assertEquals(392.69908169872417,Kugelvolumen.berechneKugelvolumen(5), delta);
            try{
                Kugelvolumen.berechneKugelvolumen(-1);
                fail("RuntimeException erwartet");
            }
            catch(RuntimeException e){
                String fehler = e.getMessage();
                assertEquals("Nix kleiner 0", fehler);
            }
        }
}
