package de.thws.lektion16.cyborg;

public abstract class Entitaet {

    protected abstract void arbeiten();
    protected abstract void autofahren();
    protected abstract Entscheidung entscheide(GefahrenSituationen gefahr);
}
