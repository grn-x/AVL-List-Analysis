package de.grnx;

import de.grnx.compiled.BSF;
import de.grnx.interpreted.*;
//import de.grnx.compiled.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n_________________________________________________________________\n<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n\nBare BSF-Tests:\n");
        de.grnx.compiled.Tests.main("1000","10");
        System.out.println("\n_________________________________________________________________\n<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>|<>\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n\nAVL-BSF-Comparison:\n");
        compareBSFtoBinarySearchTree();
        //compiledRuntime();
    }


    /** @SuppressWarnings(“unchecked”) */
    private static void compareBSFtoBinarySearchTree() {
        var contentDTO = PopulateTree.populateListRandomUnique(1000, 10);
        var entries_Compiled = contentDTO.compiled();
        var entries_Interpreted = contentDTO.interpreted();
        var entries_Interpreted_AVL = contentDTO.interpretedAVL();

/*
        //System.out.println("BSF linear search: \n" + testBSFLinearSearch(entries_Compiled));
        System.out.println("BSF (ArrayList (RandomAccess)): \n" + testBSF(entries_Compiled));
        System.gc();
        System.out.println("Binary Tree (Non balanced, dataset randomized): \n" + testBinarySearchTree(entries_Interpreted));
        System.gc();
        //System.out.println("\nBinary Tree (AVL, balanced, dataset randomized): \n" + testBinarySearchTreeAVL(entries_Interpreted_AVL));
        //System.out.println("Binary Tree (Non balanced, dataset presorted, worst-case): \n" + testBinarySearchTreePreSorted(entries_Interpreted));
        System.out.println("\nBSF (Stack (RandomAccess)): \n" + testBSF_stack(entries_Compiled));
        System.gc();
        System.out.println("\nBSF (Vector (RandomAccess)): \n" + testBSF_vector(entries_Compiled));
        System.gc();
        //System.out.println("\nBSF (LinkedList (Non RandomAccess!)): \n" + testBSF_linked(entries_Compiled));
*/


        System.out.println("BSF ArrayList linear search: \n" + testBSFLinearSearch(ArrayList.class, entries_Compiled));
        System.out.println("BSF Vector linear search: \n" + testBSFLinearSearch(Vector.class, entries_Compiled));
        System.out.println("BSF (ArrayList (RandomAccess)): \n" + testBSF(ArrayList.class, entries_Compiled));
        System.out.println("Binary Tree (Non balanced, dataset randomized): \n" + testBinarySearchTree(entries_Interpreted));
        System.out.println("\nBinary Tree (AVL, balanced, dataset randomized): \n" + testBinarySearchTreeAVL(entries_Interpreted_AVL));
        //System.out.println("Binary Tree (Non balanced, dataset presorted, worst-case): \n" + testBinarySearchTree(entries_Interpreted.clone().sort(Comparator.comparing(de.grnx.interpreted.Lexikoneintrag::getName)));
        //need to clone the dataset in case i wanna use it later
        System.out.println("Binary Tree (Non balanced, dataset presorted, worst-case): \n" + testBinarySearchTree(entries_Interpreted.stream().sorted(Comparator.comparing(de.grnx.interpreted.Lexikoneintrag::getName)).toList()));
        System.out.println("\nBSF (Stack (RandomAccess)): \n" + testBSF(Stack.class, entries_Compiled));
        System.out.println("\nBSF (Vector (RandomAccess)): \n" + testBSF(Vector.class, entries_Compiled));
        System.out.println("\nBSF (LinkedList (Non RandomAccess!)): \n" + testBSF(LinkedList.class, entries_Compiled));


System.out.println("\n_________________________________________________________________\n");


        System.out.println("BSF ArrayList linear search: \n" + testBSFLinearSearch(ArrayList.class, entries_Compiled));
        System.out.println("BSF Vector linear search: \n" + testBSFLinearSearch(Vector.class, entries_Compiled));
        System.out.println("BSF (ArrayList (RandomAccess)): \n" + testBSF_sized(ArrayList.class, entries_Compiled));
        System.out.println("Binary Tree (Non balanced, dataset randomized): \n" + testBinarySearchTree(entries_Interpreted));
        System.out.println("\nBinary Tree (AVL, balanced, dataset randomized): \n" + testBinarySearchTreeAVL(entries_Interpreted_AVL));
        //System.out.println("Binary Tree (Non balanced, dataset presorted, worst-case): \n" + testBinarySearchTree(entries_Interpreted.clone().sort(Comparator.comparing(de.grnx.interpreted.Lexikoneintrag::getName)));
        //need to clone the dataset in case i wanna use it later
        System.out.println("Binary Tree (Non balanced, dataset presorted, worst-case): \n" + testBinarySearchTree(entries_Interpreted.stream().sorted(Comparator.comparing(de.grnx.interpreted.Lexikoneintrag::getName)).toList()));
        System.out.println("\nBSF (Stack (RandomAccess)): \n" + testBSF_sized(Stack.class, entries_Compiled));
        System.out.println("\nBSF (Vector (RandomAccess)): \n" + testBSF_sized(Vector.class, entries_Compiled));
        System.out.println("\nBSF (LinkedList (Non RandomAccess!)): \n" + testBSF_sized(LinkedList.class, entries_Compiled));



    }

    private static <T extends List<de.grnx.compiled.Lexikoneintrag>> String testBSF(Class<T> listClass, List<de.grnx.compiled.Lexikoneintrag> entries, int initialListSize) {
        BSF<T, de.grnx.compiled.Lexikoneintrag> bsf;
        if(initialListSize < 0){
            bsf = new BSF<>(listClass);
        }else{
            bsf = new BSF<>(listClass, initialListSize);
        }
        var timeDiffs = new Stack<Long>();

        long insertStartNanos = System.nanoTime();
        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.insertElement(entry);
        }
        long insertEndNanos = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            long searchSingular = System.nanoTime();
            for (de.grnx.compiled.Lexikoneintrag entry : entries) {
                bsf.search(entry);
            }
            timeDiffs.push(System.nanoTime() - searchSingular);
        }

        long avg = timeDiffs.stream().mapToLong(Long::longValue).sum() / timeDiffs.size();
        long max = timeDiffs.stream().mapToLong(Long::longValue).max().getAsLong();
        long min = timeDiffs.stream().mapToLong(Long::longValue).min().getAsLong();

        var returns = new StringBuilder();
        returns.append("Underlying List Type: ").append(bsf.getType()).append("\n");
        returns.append("Num Entries List: ").append(bsf.getList().size()).append("\n");
        returns.append("Insertion time: ").append(String.format("%.3f", (insertEndNanos - insertStartNanos) / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (avg): ").append(String.format("%.3f", avg / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (max): ").append(String.format("%.3f", max / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (min): ").append(String.format("%.3f", min / 1000000.0)).append("ms\n");
        return returns.toString();
    }

    private static <T extends List<de.grnx.compiled.Lexikoneintrag>> String testBSF(Class<T> listClass, List<de.grnx.compiled.Lexikoneintrag> entries) {
        return testBSF(listClass, entries, -1);
    }


    private static <T extends List<de.grnx.compiled.Lexikoneintrag>> String testBSF_sized(Class<T> listClass, List<de.grnx.compiled.Lexikoneintrag> entries) {
        return testBSF(listClass, entries, entries.size());
    }


    private static <T extends List<de.grnx.compiled.Lexikoneintrag>> String testBSFLinearSearch(Class<T> listClass, List<de.grnx.compiled.Lexikoneintrag> entries) {
        BSF<T, de.grnx.compiled.Lexikoneintrag> bsf = new BSF<>(listClass);
        var timeDiffs = new Stack<Long>();

        long insertStartNanos = System.nanoTime();
        for (de.grnx.compiled.Lexikoneintrag entry : entries) {
            bsf.insertElement(entry);
        }
        long insertEndNanos = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            long searchSingular = System.nanoTime();
            for (de.grnx.compiled.Lexikoneintrag entry : entries) {
                for (de.grnx.compiled.Lexikoneintrag element : bsf.getList()) {
                    if (element.equals(entry)) {
                        break;
                    }
                }
            }
            timeDiffs.push(System.nanoTime() - searchSingular);
        }

        long avg = timeDiffs.stream().mapToLong(Long::longValue).sum() / timeDiffs.size();
        long max = timeDiffs.stream().mapToLong(Long::longValue).max().getAsLong();
        long min = timeDiffs.stream().mapToLong(Long::longValue).min().getAsLong();

        var returns = new StringBuilder();
        returns.append("Num Entries List (Linear Search): ").append(bsf.getList().size()).append("\n");
        returns.append("Insertion time: ").append(String.format("%.3f", (insertEndNanos - insertStartNanos) / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (avg): ").append(String.format("%.3f", avg / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (max): ").append(String.format("%.3f", max / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (min): ").append(String.format("%.3f", min / 1000000.0)).append("ms\n");

        return returns.toString();
    }

    private static String testBinarySearchTree(List<de.grnx.interpreted.Lexikoneintrag> entries) {
        de.grnx.interpreted.BinBaum binarySearchTree = new BinBaum();
        var timeDiffs = new Stack<Long>();

        long insertStartNanos = System.nanoTime();
        for (de.grnx.interpreted.Lexikoneintrag entry : entries) {
            binarySearchTree.einfuegen(entry);
        }
        long insertEndNanos = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            long searchSingular = System.nanoTime();
            for (de.grnx.interpreted.Lexikoneintrag entry : entries) {
                binarySearchTree.suchen(entry.getName());
            }
            timeDiffs.push(System.nanoTime() - searchSingular);
        }

        long avg = timeDiffs.stream().mapToLong(Long::longValue).sum() / timeDiffs.size();
        long max = timeDiffs.stream().mapToLong(Long::longValue).max().getAsLong();
        long min = timeDiffs.stream().mapToLong(Long::longValue).min().getAsLong();


        var returns = new StringBuilder();
        returns.append("Num Entries Tree: ").append(binarySearchTree.getSize()).append("\n");
        returns.append("Insertion time: ").append(String.format("%.3f", (insertEndNanos - insertStartNanos) / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (avg): ").append(String.format("%.3f", avg / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (max): ").append(String.format("%.3f", max / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (min): ").append(String.format("%.3f", min / 1000000.0)).append("ms\n");

        return returns.toString();

    }

    private static String testBinarySearchTreeAVL(List<de.grnx.interpretedAVL.Lexikoneintrag> entries) {
        de.grnx.interpretedAVL.BinBaum binarySearchTree = new de.grnx.interpretedAVL.BinBaum();
        var timeDiffs = new Stack<Long>();


        long insertStartNanos = System.nanoTime();
        for (de.grnx.interpretedAVL.Lexikoneintrag entry : entries) {
            binarySearchTree.einfuegen(entry);
        }
        long insertEndNanos = System.nanoTime();

        for(int i = 0; i < 100; i++) {
            long searchSingular = System.nanoTime();

            for (de.grnx.interpretedAVL.Lexikoneintrag entry : entries) {
                binarySearchTree.suchen(entry.getName());
            }
            timeDiffs.push(System.nanoTime() - searchSingular);
        }


        //get the average search time, the biggest and smallest
        long avg = timeDiffs.stream().mapToLong(Long::longValue).sum() / timeDiffs.size();
        long max = timeDiffs.stream().mapToLong(Long::longValue).max().getAsLong();
        long min = timeDiffs.stream().mapToLong(Long::longValue).min().getAsLong();

        var returns = new StringBuilder();
        returns.append("Num Entries Tree: ").append(binarySearchTree.getSize()).append("\n");
        returns.append("Insertion time: ").append(String.format("%.3f", (insertEndNanos - insertStartNanos) / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (avg): ").append(String.format("%.3f", avg / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (max): ").append(String.format("%.3f", max / 1000000.0)).append("ms\n");
        returns.append("\tSearch time (min): ").append(String.format("%.3f", min / 1000000.0)).append("ms\n");

        return returns.toString();
    }

}
