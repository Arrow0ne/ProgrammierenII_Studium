package de.thws.lektion15;

public class Abteilungsleiter extends Angestellter {


    public Abteilungsleiter(String nachname, String vorname, int identifikator, double grundGehalt, String geburtsdatum){
        super(nachname, vorname, identifikator, grundGehalt, geburtsdatum);
        this.gehaltsfaktor = 2;
    }

    public void befoerdern(Angestellter angestellter){
        angestellter.gehaltsfaktor *= 1.10;
    }
}
