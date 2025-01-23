package de.grnx.compiled;

interface Datenelement {
    public boolean istGleich(Datenelement neuDaten);
    public String getName();
    public boolean istGrößerAls(Datenelement neuDaten);
}