package de.thws.lektion14;

public class Person {
    String vorname;
    String nachname;
    Adresse adresse;

    public Person(String vorname, String nachname, String strasse, String hausnummer, int postleitzahl, String ort){
        if(Character.isLowerCase(vorname.charAt(0))){
            throw new IllegalArgumentException("Vorname muss mit einem Großbuchstaben beginnen");
        }
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = new Adresse(strasse, hausnummer, postleitzahl, ort);
    }
}
