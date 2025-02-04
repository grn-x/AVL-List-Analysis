package de.grnx.interpreted;

interface Baumelement {
    public Baumelement einfuegen(Datenelement neuDaten);
    public Datenelement suchen(Datenelement search_request);
    public void inOrderAusgeben();
    public void preOrderAusgeben();
    public void strukturiertPreOrderAusgeben(int tiefePar);
    public int maxHoeheTeilbaeumeBestimmen(int meineTiefe);
}