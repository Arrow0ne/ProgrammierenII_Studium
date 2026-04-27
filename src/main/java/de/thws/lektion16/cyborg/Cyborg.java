package de.thws.lektion16.cyborg;

import java.util.Random;

public class Cyborg extends Entitaet implements MenschInterface, RoboterInterface{
    private final MenschInterface mensch;
    private final RoboterInterface roboter;
    private final Random rnd;

    public Cyborg(){
        this.mensch = new Mensch();
        this.roboter = new Roboter();
        this.rnd  = new Random();
    }

    public Entscheidung entscheide(GefahrenSituationen gefahr){
        Entscheidung menschEntscheid  = mensch.entscheide(gefahr);
        Entscheidung roboterEntscheid = roboter.entscheide(gefahr);

        // Einig → gemeinsame Entscheidung
        if (menschEntscheid == roboterEntscheid) {
            return menschEntscheid;
        }

        // Uneinig → 50/50-Zufall
        return rnd.nextBoolean() ? menschEntscheid : roboterEntscheid;
    }

    public void arbeiten(){

    }
    public void autofahren() {
        System.out.println("Cyborg fährt Auto.");
        for (GefahrenSituationen gefahr : GefahrenSituationen.values()) {
            System.out.printf("  Gefahr: %-20s → %s%n", gefahr, entscheide(gefahr));
        }
    }
    public void aufladen(){

    }
    public void warten(){

    }
    public void essen(){

    }
    public void schlafen(){

    }

    public static void main(String[] args) {
        System.out.println("=== Mensch ===");
        new Mensch().autofahren();

        System.out.println("\n=== Roboter ===");
        new Roboter().autofahren();

        System.out.println("\n=== Cyborg ===");
        new Cyborg().autofahren();
    }

}
