package de.thws.lektion15;

public class Quiz {

    Quizfrage[] fragen;

    public Quiz(){
        fragen = new Quizfrage[3];

        fragen[0] = new Textfrage("Was ist die Hauptstadt Deutschland?");
        fragen[1] = new MultipleChoiceFrage("Wie viele Protonen hat ein Wasserstoff Atom", new String[]{"4", "2", "1", "0"});
        fragen[2] = new WahrFalschFrage("2 + 2 = 5");
    }

    public void ausgabe(){
        for(int i = 0; i < fragen.length; i++){
            System.out.println((i + 1) + ". Frage:");
            fragen[i].ausgabe();
            System.out.println();
        }
    }

    public static void main(String[] args){
        Quiz quiz = new Quiz();
        quiz.ausgabe();
    }
}
