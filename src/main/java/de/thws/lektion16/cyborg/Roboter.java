package de.thws.lektion16.cyborg;

public class Roboter extends Entitaet implements RoboterInterface {

    @Override
    public Entscheidung entscheide(GefahrenSituationen gefahr) {
        return switch (gefahr) {
            case GEFAHR_LINKS  -> Entscheidung.RECHTS;
            case GEFAHR_RECHTS -> Entscheidung.LINKS;
            case GEFAHR_VORNE  -> Entscheidung.BREMSEN;
            default            -> Entscheidung.BREMSEN;
        };
    }

    @Override
    public void autofahren() {
        System.out.println("=== Roboter fährt Auto ===");
        for (GefahrenSituationen gefahr : GefahrenSituationen.values()) {
            System.out.printf("  Gefahr: %-15s → %s%n", gefahr, entscheide(gefahr));
        }
    }

    @Override public void arbeiten() {}
    @Override public void aufladen() {}
    @Override public void warten()   {}
}