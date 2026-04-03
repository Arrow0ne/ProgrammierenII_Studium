package de.thws.lektion15;

public class Professor extends Person{

    public Professor(String fach){
        this.fach = fach;
    }

    @Override
    public void gibTaetigkeitAus() {
    System.out.println("Der Professor unterrichtet das Fach: " + fach);
    }
}
