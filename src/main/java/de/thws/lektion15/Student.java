package de.thws.lektion15;

public class Student extends Person {

    public Student(String fach){
        this.fach = fach;
    }


    @Override
    public void gibTaetigkeitAus() {
    System.out.println("Der Student besucht das Fach: " + fach);
    }
}
