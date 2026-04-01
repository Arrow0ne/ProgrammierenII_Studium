package de.thws.lektion14;

public class Adresse {
    String strasse;
    String hausnummer;
    int postleitzahl;
    String ort;

    public Adresse(String strasse, String hausnummer, int postleitzahl, String ort){
        if(Character.isLowerCase(strasse.charAt(0))){
            throw new IllegalArgumentException("Strasse muss mit einem Grossbuchstaben anfangen");
        }
        if(Character.isLowerCase(ort.charAt(0))){
            throw new IllegalArgumentException("Ort muss mit einem Grossbuchstaben anfangen");
        }
        if(!Character.isDigit(hausnummer.charAt(0))){
            throw new IllegalArgumentException("Hausnummer muss mit einer Ziffer beginnen");
        }
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.postleitzahl = postleitzahl;
        this.ort = ort;
    }
}
