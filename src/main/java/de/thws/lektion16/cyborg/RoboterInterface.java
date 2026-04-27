package de.thws.lektion16.cyborg;

public interface RoboterInterface {
    public Entscheidung entscheide(GefahrenSituationen gefahr);
    public void autofahren();
    public void aufladen();
    public void warten();
    public void arbeiten();
}
