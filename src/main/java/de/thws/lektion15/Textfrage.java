package de.thws.lektion15;

public class Textfrage extends Quizfrage{

    public Textfrage(String frageText){
        this.frageText = frageText;
    }

    @Override
    public void ausgabe() {
        System.out.println(frageText);
        System.out.println("Antwort: ");
        System.out.println();
        System.out.println();
    }
}
