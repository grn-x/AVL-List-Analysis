package de.grnx.interpretedAVL;

import static de.grnx.interpreted.Utils.println;
import static de.grnx.interpreted.Utils.print;


public class Knoten implements Baumelement {

    Baumelement linker_Nachfolger;
    Baumelement rechter_Nachfolger;
    Datenelement lexi;

    public Knoten(Datenelement neuDaten) {
        //so Knoten already has an end that gets replaced.
        linker_Nachfolger = new Abschluss();
        rechter_Nachfolger = new Abschluss();
        lexi = neuDaten;
    }

    public Baumelement einfuegen(Datenelement neuDaten) {
        if(!lexi.istGleich(neuDaten)) {
            if(lexi.istGrößerAls(neuDaten)) {
                //if own data is > then new data
                linker_Nachfolger = linker_Nachfolger.einfuegen(neuDaten);
            }
            else{
                rechter_Nachfolger = rechter_Nachfolger.einfuegen(neuDaten);
            }
        }
        else {
            println("Element existiert schon");
        }


        return ausgleichen();
    }

    public Baumelement ausgleichen() {
        Baumelement ergebnis = this;
        if(hoeheDifferenz(0) == -2) {
            if(linker_Nachfolger.hoeheDifferenz(0) == -1) {
                ergebnis = Rechtsrotieren();
            }else {
                linker_Nachfolger = linker_Nachfolger.Linksrotieren();
                ergebnis = Rechtsrotieren();
            }
            ergebnis = ergebnis.ausgleichen();

        }else if(hoeheDifferenz(0) == 2) {
            if(rechter_Nachfolger.hoeheDifferenz(0) == -1) {
                rechter_Nachfolger = rechter_Nachfolger.Rechtsrotieren();
                ergebnis = Linksrotieren();
            }else {
                ergebnis = Linksrotieren();
            }
            ergebnis = ergebnis.ausgleichen();
        }


        return ergebnis;
    }

    public Datenelement suchen(Datenelement search_request) {
        if(lexi.istGleich(search_request)) {
            return this.lexi;
        }
        else {
            if(lexi.istGrößerAls(search_request)) {
                return linker_Nachfolger.suchen(search_request);
            }
            return rechter_Nachfolger.suchen(search_request);
        }
    }

    public void InOrderausgeben() {
        linker_Nachfolger.InOrderausgeben();
        println(this.lexi.getName());
        rechter_Nachfolger.InOrderausgeben();
    }

    public void PreOrderAusgeben(int i) {

        linker_Nachfolger.PreOrderAusgeben(i + 1);

        rechter_Nachfolger.PreOrderAusgeben(i + 1);

    }

    public void StrukturPreOrderAusgeben(int i) {
        StrukturAusgeben(i);
        print(" (" + hoeheDifferenz(0) + ")");
        linker_Nachfolger.StrukturPreOrderAusgeben(i + 1);

        rechter_Nachfolger.StrukturPreOrderAusgeben(i + 1);
    }

    public void StrukturAusgeben(int i) {
        println("");
        while (i > 0) {
            print("   ");
            i = i - 1;
        }
        print(this.lexi.getName());
    }

    public int getHoehe(int i) {
        int a = linker_Nachfolger.getHoehe(i + 1);
        int b = rechter_Nachfolger.getHoehe(i + 1);

        if(a > b) {
            return a;
        }
        return b;
    }

    public void InvertBaum() {
        Baumelement alt = linker_Nachfolger;
        linker_Nachfolger = rechter_Nachfolger;
        rechter_Nachfolger = alt;

        linker_Nachfolger.InvertBaum();
        rechter_Nachfolger.InvertBaum();
    }

    public int hoeheDifferenz(int meineTiefe) {
        int a = linker_Nachfolger.getHoehe(meineTiefe + 1);
        int b = rechter_Nachfolger.getHoehe(meineTiefe + 1);
        return b - a;
    }

    public Baumelement Rechtsrotieren() {
        Baumelement ergebnis = linker_Nachfolger;
        linker_Nachfolger = ergebnis.getRechter_Nachfolger();
        ergebnis.setRechter_Nachfolger(this);

        return ergebnis;
    }

    public Baumelement Linksrotieren() {
        Baumelement ergebnis = rechter_Nachfolger;
        rechter_Nachfolger = ergebnis.getLinker_Nachfolger();
        ergebnis.setLinker_Nachfolger(this);

        return ergebnis;
    }

    public Baumelement getLinker_Nachfolger() {
        return linker_Nachfolger;
    }

    public Baumelement getRechter_Nachfolger() {
        return rechter_Nachfolger;
    }

    public void setLinker_Nachfolger(Baumelement neu) {
        linker_Nachfolger = neu;
    }

    public void setRechter_Nachfolger(Baumelement neu) {
        rechter_Nachfolger = neu;
    }

    public int getSize() {
        return 1 + linker_Nachfolger.getSize() + rechter_Nachfolger.getSize();
    }

}