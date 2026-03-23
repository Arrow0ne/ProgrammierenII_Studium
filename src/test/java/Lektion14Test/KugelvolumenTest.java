package Lektion14Test;
import Lektion14.Kugelvolumen;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KugelvolumenTest {


        @Test
        public void testBerechneKugelvolumen(){
            assertEquals(0.0, Kugelvolumen.berechneKugelvolumen(0), 0.000001);
            assertEquals(3.141592653589793,Kugelvolumen.berechneKugelvolumen(1), 0.000001);
            assertEquals(392.69908169872417,Kugelvolumen.berechneKugelvolumen(5), 0.000001);
            try{
                Kugelvolumen.berechneKugelvolumen(-1);
                fail("RuntimeException erwartet");
            }
            catch(RuntimeException e){
                String fehler = e.getMessage();
                assertEquals("Nix kleiner 1", fehler);
            }
        }
}
