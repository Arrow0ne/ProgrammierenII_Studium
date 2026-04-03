package de.thws.lektion15;

public class WahrFalschFrage extends Quizfrage{

    public WahrFalschFrage(String frageText){
        this.frageText = frageText;
    }

    @Override
    public void ausgabe() {
        System.out.println(frageText);
        System.out.println("Wahr oder Falsch?");
    }
}
