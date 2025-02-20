package de.grnx.compiled;

import de.grnx.PopulateTree;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Tests {

    public static void main(String... args) {
        int numEntries = 1000;
        int lenEntries = 12;

        if(args.length == 2 && args[0].matches("[0-9]+") && args[1].matches("[0-9]+")){
            numEntries = Integer.parseInt(args[0]);
            lenEntries = Integer.parseInt(args[1]);
        }

        System.out.println("numEntries = " + numEntries + ",\t lenEntries = " + lenEntries);

        long startTime = System.currentTimeMillis();
        PopulateTree.preInit();
        long endTime = System.currentTimeMillis();
        System.out.println("File Reading Operation took: " + (endTime - startTime) + "ms");

        var contentDTO = PopulateTree.populateListRandomUnique(numEntries, lenEntries);//=PopulateTree.populateListNamesUnique(100);
        Supplier<Stream<Lexikoneintrag>> streamSupplier = () -> contentDTO.compiled().stream();

        //testBSFfunctionality(streamSupplier.get());
        testBSFWithArrayList(streamSupplier);
        //call System.gc in between and then use a memory profiler to see the differences
        testBSFWithVector(streamSupplier);
        testBSFWithStack(streamSupplier);
        testBSFWithCopyOnWriteArrayList(streamSupplier);
        testBSFWithLinkedList(streamSupplier);


        /*
        // Example Output: With 12 chars over 100,000 entries

                File Reading Operation took: 12ms

                ArrayList Insertion Time: 230ms
                ArrayList Default Collection Shuffle Time: 4ms
                ArrayList Default Collection Sort Time: 108ms
                     Full Search Time: 220ms
                         Concurrent Search Time: 75ms
                         Sorted Search Time: 104ms
                         Parallel Search Time: 8ms
                         Parallel Sorted Search Time: 33ms
                    Stream Setup Time: 308000 Nano Seconds


                Vector Insertion Time: 173ms
                Vector Default Collection Shuffle Time: 7ms
                Vector Default Collection Sort Time: 54ms
                     Full Search Time: 244ms
                         Concurrent Search Time: 42ms
                         Sorted Search Time: 47ms
                         Parallel Search Time: 79ms
                         Parallel Sorted Search Time: 76ms


                Stack Insertion Time: 132ms
                Stack Default Collection Shuffle Time: 5ms
                Stack Default Collection Sort Time: 32ms
                     Full Search Time: 300ms
                         Concurrent Search Time: 34ms
                         Sorted Search Time: 60ms
                         Parallel Search Time: 111ms
                         Parallel Sorted Search Time: 95ms


                CopyOnWriteArrayList Insertion Time: 1961ms
                CopyOnWriteArrayList Default Collection Shuffle Time: 5215ms
                CopyOnWriteArrayList Default Collection Sort Time: 47ms
                     Full Search Time: 88ms
                         Concurrent Search Time: 34ms
                         Sorted Search Time: 41ms
                         Parallel Search Time: 4ms
                         Parallel Sorted Search Time: 9ms


                Warning: java.util.LinkedList is not RandomAccess
                //Edit: Holy fuck this is taking long, my poor notebook

                LinkedList Insertion Time: 157978ms
                LinkedList Default Collection Shuffle Time: 14ms
                LinkedList Default Collection Sort Time: 44ms
                     Full Search Time: 998675ms
                         Concurrent Search Time: 496865ms
                         Sorted Search Time: 420177ms
                         Parallel Search Time: 40658ms
                         Parallel Sorted Search Time: 40975ms

               //Edit: LinkedList -> 1.156.711ms = 19,28 min, thats completely insane

         */

    }

    private static void testBSFfunctionality(Stream<Lexikoneintrag> stream) {
        BSF<ArrayList<Lexikoneintrag>, Lexikoneintrag> arrayListFramework = new BSF<>(ArrayList.class);
        stream.forEach(arrayListFramework::insertElement);
        System.out.println("\n\narrayListFramework.toString() = " + arrayListFramework.toString());

    }

    private static void testBSFWithArrayList(Supplier<Stream<Lexikoneintrag>> streamSupplier) {
        long streamSetupStart = System.nanoTime();
        var insertionStream = streamSupplier.get();
        var concurrentStreamSearch = streamSupplier.get();
        var sortedStreamSearch = streamSupplier.get().sorted();
        var parallelStreamSearch = streamSupplier.get().parallel();
        var parallelSortedStreamSearch = streamSupplier.get().parallel().sorted();
        long streamSetupEnd = System.nanoTime();

        long startTime = System.currentTimeMillis();
        BSF<ArrayList<Lexikoneintrag>, Lexikoneintrag> ArrayListFramework = new BSF<>(ArrayList.class);
        insertionStream.forEach(ArrayListFramework::insertElement);
        long endTime = System.currentTimeMillis();
        ArrayListFramework.shuffleList();
        long shuffleEndTime = System.currentTimeMillis();
        ArrayListFramework.sortList();
        long sortEndTime = System.currentTimeMillis();

        concurrentStreamSearch.forEach(ArrayListFramework::search);
        long searchEndTime = System.currentTimeMillis();
        sortedStreamSearch.forEach(ArrayListFramework::search);
        long sortedSearchEndTime = System.currentTimeMillis();
        parallelStreamSearch.forEach(ArrayListFramework::search);
        long parallelSearchEndTime = System.currentTimeMillis();
        parallelSortedStreamSearch.forEach(ArrayListFramework::search);
        long parallelSortedSearchEndTime = System.currentTimeMillis();


        System.out.println("\n\nArrayList Insertion Time: " + (endTime - startTime) + "ms");
        System.out.println("ArrayList Default Collection Shuffle Time: " + (shuffleEndTime - endTime) + "ms");
        System.out.println("ArrayList Default Collection Sort Time: " + (sortEndTime - shuffleEndTime) + "ms");
        System.out.println("\t Full Search Time: " + (parallelSortedSearchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Concurrent Search Time: " + (searchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Sorted Search Time: " + (sortedSearchEndTime - searchEndTime) + "ms");
        System.out.println("\t \t Parallel Search Time: " + (parallelSearchEndTime - sortedSearchEndTime) + "ms");
        System.out.println("\t \t Parallel Sorted Search Time: " + (parallelSortedSearchEndTime - parallelSearchEndTime) + "ms");
        System.out.println("\tStream Setup Time: " + (streamSetupEnd - streamSetupStart) + " Nano Seconds");
    }

    private static void testBSFWithLinkedList(Supplier<Stream<Lexikoneintrag>> streamSupplier) {
        var insertionStream = streamSupplier.get();
        var concurrentStreamSearch = streamSupplier.get();
        var sortedStreamSearch = streamSupplier.get().sorted();
        var parallelStreamSearch = streamSupplier.get().parallel();
        var parallelSortedStreamSearch = streamSupplier.get().parallel().sorted();


        long startTime = System.currentTimeMillis();
        BSF<LinkedList<Lexikoneintrag>, Lexikoneintrag> linkedListFramework = new BSF<>(LinkedList.class);
        insertionStream.forEach(linkedListFramework::insertElement);
        long endTime = System.currentTimeMillis();
        linkedListFramework.shuffleList();
        long shuffleEndTime = System.currentTimeMillis();
        linkedListFramework.sortList();
        long sortEndTime = System.currentTimeMillis();

        concurrentStreamSearch.forEach(linkedListFramework::search);
        long searchEndTime = System.currentTimeMillis();
        sortedStreamSearch.forEach(linkedListFramework::search);
        long sortedSearchEndTime = System.currentTimeMillis();
        parallelStreamSearch.forEach(linkedListFramework::search);
        long parallelSearchEndTime = System.currentTimeMillis();
        parallelSortedStreamSearch.forEach(linkedListFramework::search);
        long parallelSortedSearchEndTime = System.currentTimeMillis();


        System.out.println("\n\nLinkedList Insertion Time: " + (endTime - startTime) + "ms");
        System.out.println("LinkedList Default Collection Shuffle Time: " + (shuffleEndTime - endTime) + "ms");
        System.out.println("LinkedList Default Collection Sort Time: " + (sortEndTime - shuffleEndTime) + "ms");
        System.out.println("\t Full Search Time: " + (parallelSortedSearchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Concurrent Search Time: " + (searchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Sorted Search Time: " + (sortedSearchEndTime - searchEndTime) + "ms");
        System.out.println("\t \t Parallel Search Time: " + (parallelSearchEndTime - sortedSearchEndTime) + "ms");
        System.out.println("\t \t Parallel Sorted Search Time: " + (parallelSortedSearchEndTime - parallelSearchEndTime) + "ms");

    }

    private static void testBSFWithVector(Supplier<Stream<Lexikoneintrag>> streamSupplier) {
        var insertionStream = streamSupplier.get();
        var concurrentStreamSearch = streamSupplier.get();
        var sortedStreamSearch = streamSupplier.get().sorted();
        var parallelStreamSearch = streamSupplier.get().parallel();
        var parallelSortedStreamSearch = streamSupplier.get().parallel().sorted();


        long startTime = System.currentTimeMillis();
        BSF<Vector<Lexikoneintrag>, Lexikoneintrag> vectorFramework = new BSF<>(Vector.class);
        insertionStream.forEach(vectorFramework::insertElement);
        long endTime = System.currentTimeMillis();
        vectorFramework.shuffleList();
        long shuffleEndTime = System.currentTimeMillis();
        vectorFramework.sortList();
        long sortEndTime = System.currentTimeMillis();

        concurrentStreamSearch.forEach(vectorFramework::search);
        long searchEndTime = System.currentTimeMillis();
        sortedStreamSearch.forEach(vectorFramework::search);
        long sortedSearchEndTime = System.currentTimeMillis();
        parallelStreamSearch.forEach(vectorFramework::search);
        long parallelSearchEndTime = System.currentTimeMillis();
        parallelSortedStreamSearch.forEach(vectorFramework::search);
        long parallelSortedSearchEndTime = System.currentTimeMillis();


        System.out.println("\n\nVector Insertion Time: " + (endTime - startTime) + "ms");
        System.out.println("Vector Default Collection Shuffle Time: " + (shuffleEndTime - endTime) + "ms");
        System.out.println("Vector Default Collection Sort Time: " + (sortEndTime - shuffleEndTime) + "ms");
        System.out.println("\t Full Search Time: " + (parallelSortedSearchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Concurrent Search Time: " + (searchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Sorted Search Time: " + (sortedSearchEndTime - searchEndTime) + "ms");
        System.out.println("\t \t Parallel Search Time: " + (parallelSearchEndTime - sortedSearchEndTime) + "ms");
        System.out.println("\t \t Parallel Sorted Search Time: " + (parallelSortedSearchEndTime - parallelSearchEndTime) + "ms");

    }

    private static void testBSFWithCopyOnWriteArrayList(Supplier<Stream<Lexikoneintrag>> streamSupplier) {
        var insertionStream = streamSupplier.get();
        var concurrentStreamSearch = streamSupplier.get();
        var sortedStreamSearch = streamSupplier.get().sorted();
        var parallelStreamSearch = streamSupplier.get().parallel();
        var parallelSortedStreamSearch = streamSupplier.get().parallel().sorted();


        long startTime = System.currentTimeMillis();
        BSF<CopyOnWriteArrayList<Lexikoneintrag>, Lexikoneintrag> cOWALFramework = new BSF<>(CopyOnWriteArrayList.class);
        insertionStream.forEach(cOWALFramework::insertElement);
        long endTime = System.currentTimeMillis();
        cOWALFramework.shuffleList();
        long shuffleEndTime = System.currentTimeMillis();
        cOWALFramework.sortList();
        long sortEndTime = System.currentTimeMillis();

        concurrentStreamSearch.forEach(cOWALFramework::search);
        long searchEndTime = System.currentTimeMillis();
        sortedStreamSearch.forEach(cOWALFramework::search);
        long sortedSearchEndTime = System.currentTimeMillis();
        parallelStreamSearch.forEach(cOWALFramework::search);
        long parallelSearchEndTime = System.currentTimeMillis();
        parallelSortedStreamSearch.forEach(cOWALFramework::search);
        long parallelSortedSearchEndTime = System.currentTimeMillis();


        System.out.println("\n\nCopyOnWriteArrayList Insertion Time: " + (endTime - startTime) + "ms");
        System.out.println("CopyOnWriteArrayList Default Collection Shuffle Time: " + (shuffleEndTime - endTime) + "ms");
        System.out.println("CopyOnWriteArrayList Default Collection Sort Time: " + (sortEndTime - shuffleEndTime) + "ms");
        System.out.println("\t Full Search Time: " + (parallelSortedSearchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Concurrent Search Time: " + (searchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Sorted Search Time: " + (sortedSearchEndTime - searchEndTime) + "ms");
        System.out.println("\t \t Parallel Search Time: " + (parallelSearchEndTime - sortedSearchEndTime) + "ms");
        System.out.println("\t \t Parallel Sorted Search Time: " + (parallelSortedSearchEndTime - parallelSearchEndTime) + "ms");


    }

    private static void testBSFWithStack(Supplier<Stream<Lexikoneintrag>> streamSupplier) {
        var insertionStream = streamSupplier.get();
        var concurrentStreamSearch = streamSupplier.get();
        var sortedStreamSearch = streamSupplier.get().sorted();
        var parallelStreamSearch = streamSupplier.get().parallel();
        var parallelSortedStreamSearch = streamSupplier.get().parallel().sorted();


        long startTime = System.currentTimeMillis();
        BSF<Stack<Lexikoneintrag>, Lexikoneintrag> stackFramework = new BSF<>(Stack.class);
        insertionStream.forEach(stackFramework::insertElement);
        long endTime = System.currentTimeMillis();
        stackFramework.shuffleList();
        long shuffleEndTime = System.currentTimeMillis();
        stackFramework.sortList();
        long sortEndTime = System.currentTimeMillis();

        concurrentStreamSearch.forEach(stackFramework::search);
        long searchEndTime = System.currentTimeMillis();
        sortedStreamSearch.forEach(stackFramework::search);
        long sortedSearchEndTime = System.currentTimeMillis();
        parallelStreamSearch.forEach(stackFramework::search);
        long parallelSearchEndTime = System.currentTimeMillis();
        parallelSortedStreamSearch.forEach(stackFramework::search);
        long parallelSortedSearchEndTime = System.currentTimeMillis();


        System.out.println("\n\nStack Insertion Time: " + (endTime - startTime) + "ms");
        System.out.println("Stack Default Collection Shuffle Time: " + (shuffleEndTime - endTime) + "ms");
        System.out.println("Stack Default Collection Sort Time: " + (sortEndTime - shuffleEndTime) + "ms");
        System.out.println("\t Full Search Time: " + (parallelSortedSearchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Concurrent Search Time: " + (searchEndTime - sortEndTime) + "ms");
        System.out.println("\t \t Sorted Search Time: " + (sortedSearchEndTime - searchEndTime) + "ms");
        System.out.println("\t \t Parallel Search Time: " + (parallelSearchEndTime - sortedSearchEndTime) + "ms");
        System.out.println("\t \t Parallel Sorted Search Time: " + (parallelSortedSearchEndTime - parallelSearchEndTime) + "ms");

    }
}