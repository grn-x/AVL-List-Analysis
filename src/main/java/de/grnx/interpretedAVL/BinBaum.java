package de.grnx.interpretedAVL;

import static de.grnx.interpreted.Utils.println;

public class BinBaum {
    Baumelement wurzel;
    public BinBaum() {
        Abschluss A1 = new Abschluss();
        wurzel = A1;
    }

    public void einfuegen(Datenelement neuDaten) {
        wurzel = wurzel.einfuegen(neuDaten);
        //wurzel = wurzel.ausgleichen();
    }
    public int getSize() {
        return wurzel.getSize();
    }


    public Datenelement suchen(String search_request) {
        Lexikoneintrag l1 = new Lexikoneintrag(search_request);
        return wurzel.suchen(l1);

    }

    public void InOrderausgeben() {
        println("Die Inorderausgabe wird gestartet:");
        wurzel.InOrderausgeben();
    }

    public void PreOrderAusgeben() {
        println("Die Preorderausgabe wird gestartet:");
        wurzel.PreOrderAusgeben(0);
        // To Do
    }

    public void StrukturPreOrderAusgeben() {
        println("Die Preorderausgabe wird gestartet:");
        wurzel.StrukturPreOrderAusgeben(0);
    }

    public void printHoehe() {
        println("");
        println("Die Höhe ist: " + wurzel.getHoehe(0));
    }
}