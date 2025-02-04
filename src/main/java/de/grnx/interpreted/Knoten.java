package de.grnx.interpreted;
import static de.grnx.interpreted.Utils.print;
import static de.grnx.interpreted.Utils.println;

public class Knoten implements Baumelement {

    Baumelement linker_Nachfolger;
    Baumelement rechter_Nachfolger;
    Datenelement lexi;

    public Knoten(Datenelement neuDaten) {
        Abschluss neuLinkerAb = new Abschluss();
        Abschluss neuRechterAb = new Abschluss();
        //so Knoten already has an end that gets replaced.
        linker_Nachfolger = neuLinkerAb;
        rechter_Nachfolger = neuRechterAb;
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
        return this;
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

    public void inOrderAusgeben() {
        linker_Nachfolger.inOrderAusgeben();
        println(this.lexi.getName());
        rechter_Nachfolger.inOrderAusgeben();
    }

    public void preOrderAusgeben() {
        println(this.lexi.getName());
        linker_Nachfolger.preOrderAusgeben();
        rechter_Nachfolger.preOrderAusgeben();
    }

    public void strukturiertPreOrderAusgeben(int tiefePar) {
        String einruecktiefe = "";
        for (int i = 0; i < tiefePar + 1; i++) {
            einruecktiefe = einruecktiefe.concat("   ");
        }
        println(einruecktiefe + this.lexi.getName());
        linker_Nachfolger.strukturiertPreOrderAusgeben(tiefePar + 1);
        rechter_Nachfolger.strukturiertPreOrderAusgeben(tiefePar + 1);
    }

    public int maxHoeheTeilbaeumeBestimmen(int meineTiefe) {
        int hoeheLinkerTeilbaum = this.linker_Nachfolger.maxHoeheTeilbaeumeBestimmen(meineTiefe + 1);
        int hoeheRechterTeilbaum = this.rechter_Nachfolger.maxHoeheTeilbaeumeBestimmen(meineTiefe + 1);
        if(hoeheLinkerTeilbaum >= hoeheRechterTeilbaum) {
            return hoeheLinkerTeilbaum;
        }
        else{
            return hoeheRechterTeilbaum;
        }
    }
}