package de.thws.lektion15;

public class MultipleChoiceFrage extends Quizfrage{

    public String[] mcAntworten;

    public MultipleChoiceFrage(String frageText, String[] mcAntworten){
        this.frageText = frageText;
        this.mcAntworten = mcAntworten;
    }

    @Override
    public void ausgabe() {
        char buchstabe = 'A';
        System.out.println(frageText);
        System.out.println("Antworten: ");
        for(int i = 0; i < mcAntworten.length; i++) {
            System.out.println(buchstabe + ": " + mcAntworten[i]);
            buchstabe++;
        }

    }
}
