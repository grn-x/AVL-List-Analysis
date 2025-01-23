package de.grnx.compiled;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchFramework<T extends List<E>, E> {

    private T list;

//    public BinarySearchFramework(Class<T> clazz) {
//        try {
//            Constructor<T> constructor = clazz.getDeclaredConstructor();
//            this.list = constructor.newInstance();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to instantiate list", e);
//        }
//    }


    public BinarySearchFramework(Class clazz) { //this urgently needs to be corrected to Class<T> clazz otherwise the type of list is not correctly inferred, ex passing a random class
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
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

    public static void main(String[] args) {
        BinarySearchFramework<ArrayList<String>, String> arrayListFramework = new BinarySearchFramework<ArrayList<String>, String>(ArrayList.class);
        arrayListFramework.addElement("apple");
        arrayListFramework.addElement("banana");
        arrayListFramework.addElement("cherry");
        arrayListFramework.shuffleList();
        arrayListFramework.sortList();
        System.out.println(arrayListFramework.getList());

        BinarySearchFramework<LinkedList<String>, String> linkedListFramework = new BinarySearchFramework<>(LinkedList.class);
        linkedListFramework.addElement("apple");
        linkedListFramework.addElement("banana");
        linkedListFramework.addElement("cherry");
        linkedListFramework.shuffleList();
        linkedListFramework.sortList();
        System.out.println(linkedListFramework.getList());
    }
}