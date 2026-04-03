package de.thws.lektion15;

public abstract class Person {

    public String fach;

    public abstract void gibTaetigkeitAus();

    public static void main(String[] args){

        Person[] personen = new Person[100];

        for(int i = 0; i <= 99; i++){
            if(i%2 == 0){
                personen[i] = new Student("programmieren");
            }else{
                personen[i] = new Professor("programmieren");
            }
            System.out.print((i + 1) + ". ");
            personen[i].gibTaetigkeitAus();
        }
    }
}

/*In JUnit testen ob gibTaetigkeit bei beiden das richtig ausgibt und ob die Kostruktor die
die richtigen Werte übernimmt*/