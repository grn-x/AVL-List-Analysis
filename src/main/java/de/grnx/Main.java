package de.grnx;
import static de.grnx.interpreted.Utils.*;
import static java.lang.Math.random;

import de.grnx.interpreted.*;
//import de.grnx.compiled.*;

import java.util.ArrayList;
import java.util.Random;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        interpretedRuntime();
        System.out.println("\n_________________________________________________________________\n<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n\nCompiled runtime:\n");
        de.grnx.compiled.Tests.main(new String[0]);
        //compiledRuntime();
    }

    private static void interpretedRuntime() {
        ArrayList<Lexikoneintrag> liste = new ArrayList<>();
        int index = 0;
        BinBaum B1 = new BinBaum();
        Lexikoneintrag L1 = new Lexikoneintrag("man");
        Lexikoneintrag L2 = new Lexikoneintrag("blood");
        Lexikoneintrag L3 = new Lexikoneintrag("call");
        Lexikoneintrag L4 = new Lexikoneintrag("escape");
        Lexikoneintrag L5 = new Lexikoneintrag("car");
        Lexikoneintrag L6 = new Lexikoneintrag("rocket");
        Lexikoneintrag L7 = new Lexikoneintrag("idiot");
        Lexikoneintrag L8 = new Lexikoneintrag("help");
        for (int j = 0; j < 10; j++) {
            B1.reset();
            //   B1.wurzel = new Abschluss();
            liste.add(L1);
            liste.add(L2);
            liste.add(L3);
            liste.add(L4);
            liste.add(L5);
            liste.add(L6);
            liste.add(L7);
            liste.add(L8);
            for (int i = liste.size() - 1; i >= 0; i--) {
                //index = Random.randint(0, i);
                index = new Random().nextInt(i+1);
                B1.einfuegen(liste.get(index));
                liste.remove(index);//do all of the elements really have the same probability of being picked? i feel
                // like the lower ones are being preferred?
            }
            B1.strukturiertPreOrderAusgeben();
            B1.hoeheBaumBestimmen();
            B1.inOrderausgeben();
            println("----------------");
        }

            /*
            B1.einfuegen(L6);
            B1.einfuegen(L4);
            B1.einfuegen(L2);
            B1.einfuegen(L1);
            B1.einfuegen(L5);
            B1.einfuegen(L8);
            B1.einfuegen(L7);
            B1.einfuegen(L3);
            println("-----------------");
            println(B1.suchen("help").getName());
            println(B1.suchen("call").getName());
            println(B1.suchen("carr").getName());
            println(B1.suchen("car").getName());
            println("-----------------");
            B1.inOrderausgeben();
            println("-----------------");
            B1.preOrderausgeben();
            println("-----------------");
            B1.strukturiertPreOrderAusgeben();
            */
    }
}
