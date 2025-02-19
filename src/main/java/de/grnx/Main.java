package de.grnx;
import static de.grnx.interpreted.Utils.*;

import de.grnx.compiled.BSF;
import de.grnx.interpreted.*;
//import de.grnx.compiled.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n_________________________________________________________________\n<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n\nCompiled runtime:\n");
        de.grnx.compiled.Tests.main(new String[0]);
        System.out.println("\n_________________________________________________________________\n<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n\nCompiled runtime:\n");
        compareBSFtoBinarySearchTree();
        //compiledRuntime();
    }


    /** @SuppressWarnings(“unchecked”) */
    private static void compareBSFtoBinarySearchTree() {
        //List<de.grnx.compiled.Lexikoneintrag> entries_Compiled = new ArrayList<>();
        //var entries_Interpreted = de.grnx.PopulateTree.populateListRef_Duplicates_dual_large(entries_Compiled, 10000, 1000); // Populate with 10,000 entries

        /*List<de.grnx.compiled.Lexikoneintrag> entries_Compiled = new ArrayList<>();
        var entries_amalgamation = de.grnx.PopulateTree.populateListRef_Duplicates_trio_large(entries_Compiled, 10000, 10); // Populate with 10,000 entries
        List<de.grnx.interpreted.Lexikoneintrag> entries_Interpreted = (List<de.grnx.interpreted.Lexikoneintrag>)entries_amalgamation[0]; //holy fuck is this unsafe
        List<de.grnx.interpretedAVL.Lexikoneintrag> entries_Interpreted_AVL = (List<de.grnx.interpretedAVL.Lexikoneintrag>)entries_amalgamation[1];
        */

        var contentDTO = PopulateTree.populateListRandomUnique(10000, 10);
        var entries_Compiled = contentDTO.compiled();
        var entries_Interpreted = contentDTO.interpreted();
        var entries_Interpreted_AVL = contentDTO.interpretedAVL();

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



}
