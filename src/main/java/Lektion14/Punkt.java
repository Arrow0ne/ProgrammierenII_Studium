package Lektion14;

public class Punkt {
    public int x;
    public int y;

    public void verschiebePunkt(int zielX, int zielY) {
        if(zielX <= 0 || zielY <= 0 || zielX > 1920 || zielY > 1080){
            throw new RuntimeException("ungueltige Werte");
        }
        x = zielX;
        y = zielY;
    }
}
