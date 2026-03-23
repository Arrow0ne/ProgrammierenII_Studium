package Lektion14Test;
import Lektion14.Punkt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PunktTest {
    @Test
    public void testVerschiebePunkt(){
        Punkt test1 = new Punkt();
        test1.verschiebePunkt(5,10);
        assertEquals(5,test1.x);
        assertEquals(10,test1.y);
        try{
            test1.verschiebePunkt(-1,-2);
            fail("RuntimeException erwartet");
        } catch (RuntimeException e){
            String fehler = e.getMessage();
            assertEquals("ungueltige Werte", fehler);
        }
        try{
            test1.verschiebePunkt(2000,5);
            fail("RuntimeException erwartet");
        } catch (RuntimeException e){
            String fehler = e.getMessage();
            assertEquals("ungueltige Werte", fehler);
        }
        try{
            test1.verschiebePunkt(5,1130);
            fail("RuntimeException erwartet");
        } catch (RuntimeException e){
            String fehler = e.getMessage();
            assertEquals("ungueltige Werte", fehler);
        }
    }
}
