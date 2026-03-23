package Lektion14;

public class Strecke {

    public int A;
    public int B;

    public Strecke(int zahl1, int zahl2) {
        if(zahl1 < 0 || zahl2 < 0){
            throw new IllegalArgumentException("Nur ganzzahlige positive Zahlen");
        }
        if (zahl2 >= zahl1) {
            this.A = zahl1;
            this.B = zahl2;
        } else {
            this.A = zahl2;
            this.B = zahl1;
        }
    }

    public boolean ueberschneidung(Strecke strecke2) {
        return this.A < strecke2.B && strecke2.A < this.B;
    }

    @Override
    public String toString(){
        String ergebnis = String.valueOf(this.A);
        int anzahlBindeStriche = this.B - this.A;
        for(int i = 1; i <= anzahlBindeStriche; i++){
            ergebnis = ergebnis + "-";
        }
        if(this.A != this.B){
            ergebnis = ergebnis + String.valueOf(this.B);
        }
        return ergebnis;
    }


}
