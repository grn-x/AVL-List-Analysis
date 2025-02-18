package de.grnx;
import static de.grnx.interpreted.Utils.*;
import static java.lang.Math.random;

import de.grnx.compiled.BSF;
import de.grnx.interpreted.*;
//import de.grnx.compiled.*;

import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        interpretedRuntime();
        System.out.println("\n_________________________________________________________________\n<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n\nCompiled runtime:\n");
        de.grnx.compiled.Tests.main(new String[0]);
        System.out.println("\n_________________________________________________________________\n<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n\nCompiled runtime:\n");
        compareBSFtoBinarySearchTree();
        //compiledRuntime();
    }


    /** @SuppressWarnings(“unchecked”) */
    private static void compareBSFtoBinarySearchTree() {
        //List<de.grnx.compiled.Lexikoneintrag> entries_Compiled = new ArrayList<>();
        //var entries_Interpreted = de.grnx.compiled.util.PopulateTree.populateListRef_Duplicates_dual_large(entries_Compiled, 10000, 1000); // Populate with 10,000 entries

        List<de.grnx.compiled.Lexikoneintrag> entries_Compiled = new ArrayList<>();
        var entries_amalgamation = de.grnx.compiled.util.PopulateTree.populateListRef_Duplicates_trio_large(entries_Compiled, 10000, 10); // Populate with 10,000 entries
        List<de.grnx.interpreted.Lexikoneintrag> entries_Interpreted = (List<de.grnx.interpreted.Lexikoneintrag>)entries_amalgamation[0]; //holy fuck is this unsafe
        List<de.grnx.interpretedAVL.Lexikoneintrag> entries_Interpreted_AVL = (List<de.grnx.interpretedAVL.Lexikoneintrag>)entries_amalgamation[1];



        System.out.println("\n");

        long bsfDuration = testBSF(entries_Compiled);
        System.out.println("BSF (ArrayList (RandomAccess)) search time: " + bsfDuration + "ms\n");

        //entries_Interpreted.sort(Comparator.comparing(de.grnx.interpreted.Lexikoneintrag::getName));
        long bstDuration = testBinarySearchTree(entries_Interpreted);
        System.out.println("Binary search tree search time: " + bstDuration + "ms\n");

        long bstAVLDuration = testBinarySearchTreeAVL(entries_Interpreted_AVL);
        System.out.println("Binary search tree (AVL, balanced) search time: " + bstAVLDuration + "ms\n");


        long bsfLinearDuration = testBSFLinearSearch(entries_Compiled);
        System.out.println("BSF linear search time is " + bsfLinearDuration + "ms\n");

        long bsfDuration_linked = testBSF_linked(entries_Compiled);
        System.out.println("BSF (LinkedList (Non RandomAccess!)) search time: " + bsfDuration_linked + "ms\n");




        /* Example Output:
                _________________________________________________________________
                <>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>
                ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾

                Compiled runtime:



                Num Entries List: 10000
                BSF (ArrayList (RandomAccess)) search time: 399ms

                Num Entries Tree: 10000
                Binary search tree search time: 412ms

                Num Entries Tree: 10000
                Binary search tree (AVL, balanced) search time: 332ms

                Num Entries List (Linear Search): 10000
                BSF linear search time is 12321ms

                Warning: List is not RandomAccess
                Num Entries List: 10000
                BSF (LinkedList (Non RandomAccess!)) search time: 197491ms

         */

    }

    private static long testBSF(List<de.grnx.compiled.Lexikoneintrag> entries) {
        BSF<ArrayList<de.grnx.compiled.Lexikoneintrag>, de.grnx.compiled.Lexikoneintrag> bsf = new BSF<>(ArrayList.class);

        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.insertElement(entry);
        }

        //System.out.println("bsf.toString() = " + bsf.toString());

        long bsfStartTime = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.search(entry);
        }
        }
        long bsfEndTime = System.currentTimeMillis();
        System.out.println("Num Entries List: " + bsf.getList().size());
        return bsfEndTime - bsfStartTime;
    }


    private static long testBSF_linked(List<de.grnx.compiled.Lexikoneintrag> entries) {
        BSF<LinkedList<de.grnx.compiled.Lexikoneintrag>, de.grnx.compiled.Lexikoneintrag> bsf = new BSF<>(LinkedList.class);

        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.insertElement(entry);
        }

        long bsfStartTime = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            for (de.grnx.compiled.Lexikoneintrag entry : entries) {
                bsf.search(entry);
            }
        }
        long bsfEndTime = System.currentTimeMillis();
        System.out.println("Num Entries List: " + bsf.getList().size());
        return bsfEndTime - bsfStartTime;
    }

    private static long testBSFLinearSearch(List<de.grnx.compiled.Lexikoneintrag> entries) {
        BSF<ArrayList<de.grnx.compiled.Lexikoneintrag>, de.grnx.compiled.Lexikoneintrag> bsf = new BSF<>(ArrayList.class);

        // Insert entries into BSF
        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.insertElement(entry);
        }

        // Perform linear search and time the BSF
        long bsfStartTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (de.grnx.compiled.Lexikoneintrag entry : entries) {
                for (de.grnx.compiled.Lexikoneintrag element : bsf.getList()) {
                    if (element.equals(entry)) {
                        break;
                    }
                }
            }
        }
        long bsfEndTime = System.currentTimeMillis();
        System.out.println("Num Entries List (Linear Search): " + bsf.getList().size());
        return bsfEndTime - bsfStartTime;
    }

    private static long testBinarySearchTree(List<de.grnx.interpreted.Lexikoneintrag> entries) {
        de.grnx.interpreted.BinBaum binarySearchTree = new BinBaum();

        for (de.grnx.interpreted.Lexikoneintrag entry : entries) {
            binarySearchTree.einfuegen(entry);
        }

        long bstStartTime = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
        for (de.grnx.interpreted.Lexikoneintrag entry : entries) {
            binarySearchTree.suchen(entry.getName());
        }
        }
        long bstEndTime = System.currentTimeMillis();
        System.out.println("Num Entries Tree: " + binarySearchTree.getSize());
        return bstEndTime - bstStartTime;
    }

    private static long testBinarySearchTreeAVL(List<de.grnx.interpretedAVL.Lexikoneintrag> entries) {
        de.grnx.interpretedAVL.BinBaum binarySearchTree = new de.grnx.interpretedAVL.BinBaum();

        for (de.grnx.interpretedAVL.Lexikoneintrag entry : entries) {
            binarySearchTree.einfuegen(entry);
        }

        long bstStartTime = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            for (de.grnx.interpretedAVL.Lexikoneintrag entry : entries) {
                binarySearchTree.suchen(entry.getName());
            }
        }
        long bstEndTime = System.currentTimeMillis();
        System.out.println("Num Entries Tree: " + binarySearchTree.getSize());
        return bstEndTime - bstStartTime;
    }

    private static long testBinarySearchTree_AVL(List<de.grnx.interpreted.Lexikoneintrag> entries) {
        BinBaum binarySearchTree = new BinBaum();

        for (de.grnx.interpreted.Lexikoneintrag entry : entries) {
            binarySearchTree.einfuegen(entry);
        }

        long bstStartTime = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            for (de.grnx.interpreted.Lexikoneintrag entry : entries) {
                binarySearchTree.suchen(entry.getName());
            }
        }
        long bstEndTime = System.currentTimeMillis();
        System.out.println("Num Entries Tree: " + binarySearchTree.getSize());
        return bstEndTime - bstStartTime;
    }

    private static void compareBSFtoBinarySearchTree_dpr() {
        BSF<ArrayList<de.grnx.compiled.Lexikoneintrag>, de.grnx.compiled.Lexikoneintrag> bsf = new BSF<>(ArrayList.class);
        BinBaum binarySearchTree = new BinBaum();
        List<de.grnx.compiled.Lexikoneintrag> entries = new ArrayList<>();
        PopulateTree.populateListRef(entries, 100, 0);
        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.insertElement(entry);
            binarySearchTree.einfuegen(entry);
        }

        long bsfStartTime = System.currentTimeMillis();
        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.search(entry);
        }
        long bsfEndTime = System.currentTimeMillis();
        long bsfDuration = bsfEndTime - bsfStartTime;

        long bstStartTime = System.currentTimeMillis();
        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            binarySearchTree.suchen(entry.getName());
        }
        long bstEndTime = System.currentTimeMillis();
        long bstDuration = bstEndTime - bstStartTime;

        System.out.println("BSF search time: " + bsfDuration + "ms");
        System.out.println("Binary search tree search time: " + bstDuration + "ms");
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
