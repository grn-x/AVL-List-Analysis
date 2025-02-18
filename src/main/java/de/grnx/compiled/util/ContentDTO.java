package de.grnx.compiled.util;

import de.grnx.interpreted.Lexikoneintrag;

import java.util.ArrayList;

public record ContentDTO(ArrayList<de.grnx.compiled.Lexikoneintrag> compiled, ArrayList<de.grnx.interpreted.Lexikoneintrag> interpreted, ArrayList<de.grnx.interpretedAVL.Lexikoneintrag> interpretedAVL) {
   /* public ContentDTO {
    this.compiled = compiled;
    this.interpreted = interpreted;
    this.interpretedAVL = interpretedAVL;
    }*/
}
