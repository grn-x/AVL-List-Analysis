package de.grnx.interpretedAVL;

interface Baumelement {
    public Baumelement einfuegen(Datenelement neuDaten);
    public Datenelement suchen(Datenelement search_request);
    public void InOrderausgeben();
    public void PreOrderAusgeben(int i);
    public void StrukturPreOrderAusgeben(int i);
    public int getHoehe(int i);
    public void InvertBaum();

    public int hoeheDifferenz(int t);
    public Baumelement ausgleichen();
    public Baumelement Rechtsrotieren();
    public Baumelement Linksrotieren();
    public Baumelement getRechter_Nachfolger();
    public Baumelement getLinker_Nachfolger();
    public void setRechter_Nachfolger(Baumelement neu);
    public void setLinker_Nachfolger(Baumelement neu);
    public int getSize();

}