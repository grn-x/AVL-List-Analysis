package de.grnx;

import de.grnx.compiled.Lexikoneintrag;
import de.grnx.compiled.util.FileHandler;
import de.grnx.compiled.util.PopulateTree;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        var list = new ArrayList<Lexikoneintrag>();
        PopulateTree.populateListRef(list, 1000, 100);
        for (var item : list) {
            System.out.println(item.getName());
        }



        }
    }
