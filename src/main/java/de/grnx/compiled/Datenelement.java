package de.grnx.compiled;

interface Datenelement extends Comparable<Datenelement>{
    public boolean istGleich(Datenelement neuDaten);
    public String getName();
    public boolean istGrößerAls(Datenelement neuDaten);
    public String toString();
}