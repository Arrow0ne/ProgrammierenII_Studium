package de.thws.lektion16.cyborg;

public class Mensch extends Entitaet implements MenschInterface {

    @Override
    public Entscheidung entscheide(GefahrenSituationen gefahr) {
        int rnd = (int) (Math.random() * 4); // Klammern beachten!
        if (rnd == 1) {
            return Entscheidung.UNENTSCHIEDEN;
        } else {
            return switch (gefahr) {
                case GEFAHR_LINKS  -> Entscheidung.RECHTS;
                case GEFAHR_RECHTS -> Entscheidung.LINKS;
                case GEFAHR_VORNE  -> Entscheidung.BREMSEN;
                default            -> Entscheidung.BREMSEN;
            };
        }
    }

    @Override
    public void autofahren() {
        System.out.println("=== Mensch fährt Auto ===");
        for (GefahrenSituationen gefahr : GefahrenSituationen.values()) {
            System.out.printf("  Gefahr: %-15s → %s%n", gefahr, entscheide(gefahr));
        }
    }

    @Override public void arbeiten() {}
    @Override public void essen()    {}
    @Override public void schlafen() {}
}