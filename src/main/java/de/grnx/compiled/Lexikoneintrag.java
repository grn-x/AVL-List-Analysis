package de.grnx.compiled;

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
    public boolean istGrößerAls(Datenelement neuDaten) {
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


    public void setName(String neuName) {
        this.name = neuName;
    }

    @Override
    public int compareTo(Datenelement o) {
        return this.name.compareTo(o.getName());
    }
    public String toString() {
        return this.name;
    }
}