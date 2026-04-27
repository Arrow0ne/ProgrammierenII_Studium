package de.thws.lektion16.cyborg;

public interface MenschInterface {
    public Entscheidung entscheide(GefahrenSituationen gefahr);
    public void essen();
    public void schlafen();
    public void arbeiten();
    public void autofahren();
}
