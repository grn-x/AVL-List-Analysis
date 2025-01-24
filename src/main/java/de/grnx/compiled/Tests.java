package de.grnx.compiled;

import de.grnx.compiled.util.PopulateTree;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tests {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        PopulateTree.preInit();
        long endTime = System.currentTimeMillis();
        System.out.println("File Reading Operation took: " + (endTime - startTime) + "ms");

        testBSFfunctionality();
        testBSFWithArrayList();
        testBSFWithLinkedList();
        testBSFWithVector();
        testBSFWithCopyOnWriteArrayList();
        testBSFWithStack();
    }

    private static void testBSFfunctionality(){
        BSF<ArrayList<Lexikoneintrag>, Lexikoneintrag> arrayListFramework = new BSF<>(ArrayList.class);
        var list = new ArrayList<Lexikoneintrag> ();
        PopulateTree.populateListRef(list, 1000, 0);
        for (var item : list) {
            arrayListFramework.insertElement(item);
        }
        System.out.println("arrayListFramework.toString() = " + arrayListFramework.toString());

    }

    private static void testBSFWithArrayList() {
        long startTime = System.currentTimeMillis();
        BSF<ArrayList<Lexikoneintrag>, Lexikoneintrag> arrayListFramework = new BSF<>(ArrayList.class);
        PopulateTree.populateListRef(arrayListFramework.getList(), 1000, 10);
        arrayListFramework.shuffleList();
        arrayListFramework.sortList();
        long endTime = System.currentTimeMillis();
        int index = arrayListFramework.search(arrayListFramework.get(arrayListFramework.size() - 1));
        System.out.println("Searched Index: " + index);
        System.out.println("ArrayList Test Time: " + (endTime - startTime) + "ms");
        System.out.println("arrayListFramework.toString() = " + arrayListFramework.toString());

    }

    private static void testBSFWithLinkedList() {
        long startTime = System.currentTimeMillis();
        BSF<LinkedList<Lexikoneintrag>, Lexikoneintrag> linkedListFramework = new BSF<>(LinkedList.class);
        PopulateTree.populateListRef(linkedListFramework.getList(), 1000, 10);
        linkedListFramework.shuffleList();
        linkedListFramework.sortList();
        long endTime = System.currentTimeMillis();
        System.out.println("LinkedList Test Time: " + (endTime - startTime) + "ms");
    }

    private static void testBSFWithVector() {
        long startTime = System.currentTimeMillis();
        BSF<Vector<Lexikoneintrag>, Lexikoneintrag> vectorFramework = new BSF<>(Vector.class);
        PopulateTree.populateListRef(vectorFramework.getList(), 1000, 10);
        vectorFramework.shuffleList();
        vectorFramework.sortList();
        long endTime = System.currentTimeMillis();
        System.out.println("Vector Test Time: " + (endTime - startTime) + "ms");
    }

    private static void testBSFWithCopyOnWriteArrayList() {
        long startTime = System.currentTimeMillis();
        BSF<CopyOnWriteArrayList<Lexikoneintrag>, Lexikoneintrag> cOWALFramework = new BSF<>(CopyOnWriteArrayList.class);
        PopulateTree.populateListRef(cOWALFramework.getList(), 1000, 10);
        cOWALFramework.shuffleList();
        cOWALFramework.sortList();
        long endTime = System.currentTimeMillis();
        System.out.println("CopyOnWriteArrayList Test Time: " + (endTime - startTime) + "ms");
    }

    private static void testBSFWithStack() {
        long startTime = System.currentTimeMillis();
        BSF<Stack<Lexikoneintrag>, Lexikoneintrag> stackFramework = new BSF<>(Stack.class);
        PopulateTree.populateListRef(stackFramework.getList(), 1000, 10);
        stackFramework.shuffleList();
        stackFramework.sortList();
        long endTime = System.currentTimeMillis();
        System.out.println("Stack Test Time: " + (endTime - startTime) + "ms");
    }
}