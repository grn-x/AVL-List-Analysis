package de.grnx.interpretedAVL;

import static de.grnx.interpreted.Utils.print;

public class Abschluss implements Baumelement {

    public Baumelement einfuegen(Datenelement neuDaten) {
        //print("Ende erreicht");
        Knoten k1 = new Knoten(neuDaten);
        return k1;
    }
    public Datenelement suchen(Datenelement search_request) {
        print("Datenelement ist nicht vorhanden: ");
        return search_request;
    }
    public void InOrderausgeben() {
        // Do nothing!
        // println("Ende des Zweigs");
    }
    public void PreOrderAusgeben(int i) { }

    public void StrukturPreOrderAusgeben(int i) {

    }

    public int getHoehe(int i) {
        return i - 1;
    }

    public Baumelement ausgleichen() {
        return this;
    }

    public int hoeheDifferenz(int i) {
        return i - 1;
    }

    public void InvertBaum() { }

    public Baumelement Rechtsrotieren() {
        return this;
    }

    public Baumelement Linksrotieren() {
        return this;
    }

    public Baumelement getLinker_Nachfolger() {
        return this;
    }

    public Baumelement getRechter_Nachfolger() {
        return this;
    }

    public void setLinker_Nachfolger(Baumelement neu) {

    }

    public void setRechter_Nachfolger(Baumelement neu) {

    }

    public int getSize() {
        return 0;
    }

}