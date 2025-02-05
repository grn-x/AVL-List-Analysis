package de.grnx.interpreted;
import static de.grnx.interpreted.Utils.print;
import static de.grnx.interpreted.Utils.println;

public class BinBaum {
    Baumelement wurzel;
    public BinBaum() {
        Abschluss A1 = new Abschluss();
        wurzel = A1;
    }

    public void reset() {
        wurzel = new Abschluss();
    }


    public void einfuegen(Datenelement neuDaten) {
        wurzel = wurzel.einfuegen(neuDaten);
    }

    public Datenelement suchen(String search_request) {
        Lexikoneintrag l1 = new Lexikoneintrag(search_request);
        return wurzel.suchen(l1);

    }

    public int getSize() {
        return wurzel.getSize();
    }

    public void inOrderausgeben() {
        println("Die Inorderausgabe wird gestartet:");
        wurzel.inOrderAusgeben();
    }

    public void preOrderausgeben() {
        println("Die Preorderausgabe wird gestartet:");
        wurzel.preOrderAusgeben();
        // To Do
    }

    public void strukturiertPreOrderAusgeben() {
        println("Die strukturierte Preorderausgabe wird gestartet:");
        wurzel.strukturiertPreOrderAusgeben(0);
        // To Do
    }

    public void hoeheBaumBestimmen() {
        println("Die Höhe des Baumes beträgt: " + wurzel.maxHoeheTeilbaeumeBestimmen(0));
    }
}