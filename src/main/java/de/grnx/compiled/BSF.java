package de.grnx.compiled;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
//TODO implement precoded binary search framework, with sorted adding of items or reordering of items after adding
/**
 * Generic class BinarySearchFramework that can be used to instantiate a list of a specific type and perform operations on it.
 * @param <T> The type of list to be instantiated
 * @param <E> The type of elements in the list
 */
public class BSF<T extends List<E>, E> {
    private T list;

    public BSF(Class<? extends T> clazz) {
        try {
            Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            this.list = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate list", e);
        }
    }

    public void addElement(E element) {
        list.add(element);
    }

    public void shuffleList() {
        Collections.shuffle(list);
    }

    public void sortList() {
        Collections.sort((List<Comparable>) list);
    }

    public List<E> getList() {
        return list;
    }

    public String toString() {
        return list.getClass().getTypeName() + "\n" + list.toString();
    }

    public static void main(String[] args) {
        BSF<ArrayList<String>, String> arrayListFramework = new BSF<>(ArrayList.class);
        arrayListFramework.addElement("apple");
        arrayListFramework.addElement("banana");
        arrayListFramework.addElement("cherry");
        arrayListFramework.shuffleList();
        arrayListFramework.sortList();
        System.out.println(arrayListFramework.getList());

        BSF<LinkedList<String>, String> linkedListFramework = new BSF<>(LinkedList.class);
        linkedListFramework.addElement("apple");
        linkedListFramework.addElement("banana");
        linkedListFramework.addElement("cherry");
        linkedListFramework.shuffleList();
        linkedListFramework.sortList();
        System.out.println(linkedListFramework.getList());


        BSF<Vector<String>, String> vectorFramework = new BSF<>(Vector.class);
        vectorFramework.addElement("apple");
        vectorFramework.addElement("banana");
        vectorFramework.addElement("cherry");
        vectorFramework.shuffleList();
        vectorFramework.sortList();
        System.out.println(vectorFramework.getList());

        BSF<CopyOnWriteArrayList<String>, String> cOWAL = new BSF<>(CopyOnWriteArrayList.class);
        cOWAL.addElement("apple");
        cOWAL.addElement("banana");
        cOWAL.addElement("cherry");
        cOWAL.shuffleList();
        cOWAL.sortList();
        System.out.println(cOWAL.getList());

        BSF<Stack<String>, String> stackFramework = new BSF<>(Stack.class);
        stackFramework.addElement("apple");
        stackFramework.addElement("banana");
        stackFramework.addElement("cherry");
        stackFramework.shuffleList();
        stackFramework.sortList();
        System.out.println("stackFramework.toString() = " + stackFramework.toString());
        System.out.println(stackFramework.getList());
    }
}