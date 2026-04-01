package de.thws.lektion15;

public class Angestellter {

    String nachname;
    String vorname;
    int identifikator;
    double grundGehalt;
    double gehaltsfaktor;
    String geburtsdatum;


    public Angestellter(String nachname, String vorname, int identifikator, double grundGehalt, String geburtsdatum){
        this.nachname = nachname;
        this.vorname = vorname;
        this.identifikator = identifikator;
        this.grundGehalt = grundGehalt;
        this.gehaltsfaktor = 1.0;
        this.geburtsdatum = geburtsdatum;
    }

    public double getGehalt() {
        return gehaltsfaktor * grundGehalt;
    }
}
