package de.grnx.interpreted;

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
    public void preOrderAusgeben() {
        // Do nothing!
        // println("Ende des Zweigs");
    }

    public void inOrderAusgeben() {
        // Do nothing!
        // println("Ende des Zweigs");
    }

    public int maxHoeheTeilbaeumeBestimmen(int meineTiefe) {
        return meineTiefe-1;
    }

    public void strukturiertPreOrderAusgeben(int tiefePar) {
        // Do nothing!
    }
}