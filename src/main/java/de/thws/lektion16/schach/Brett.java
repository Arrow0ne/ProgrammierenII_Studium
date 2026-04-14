package de.thws.lektion16.schach;

public class Brett
{
  boolean[][] brett = new boolean[8][8];
  
  public Brett()
  {
  }
  
  public void markiereFeld(int x, int y)
  {
    brett[x-1][y-1] = true;
  }
  
  public boolean gibFeld(int x, int y)
  {
    return brett[x-1][y-1];
  }

    public Brett kombiniere(Brett tempBrett) {
        Brett newBrett = new Brett();
        for (int i = 0; i < 8; i++) {
            for (int t = 0; t < 8; t++) {
                if (this.brett[i][t] || tempBrett.brett[i][t]) {
                    newBrett.brett[i][t] = true;
                }
            }
        }
        return newBrett;
    }
}