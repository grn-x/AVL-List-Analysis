package de.grnx.interpreted;

public class Lexikoneintrag implements Datenelement {

    String name;
    public Lexikoneintrag(String neuName) {
        name = neuName;
    }


    public boolean istGleich(Datenelement neuDaten) {
        if(!this.name.equals(neuDaten.getName())) {
            // if not equal !
            return false;
        }
        //else
        return true;
    }


    public String getName() {
        return this.name;
    }

    public boolean istGrößerAls_dpr(Datenelement neuDaten) {
        // -1 if this.name < neuDaten.name
        // +1 if this.name > neuDaten.name
        if(this.name.compareTo(neuDaten.getName()) == -1) {
            // Aktuelles Datenelement ALSO NICHT Größer
            // KORREKTUR false statt true
            return false;
        }
        // KORREKTUR true statt false
        return true;
    }
    public boolean istGrößerAls(Datenelement neuDaten) {
        //Alex's Online IDE version doesnt seem to work here for whatever reason, either way this solution is much more elegant.
        return this.name.compareTo(neuDaten.getName()) > 0;
    }


    public void setName(String neuName) {
        this.name = neuName;
    }
}