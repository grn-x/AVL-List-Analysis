package de.grnx.compiled;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchFramework<T extends List<E>, E> {//use wildcards instead? :/

    private T list;

    public BinarySearchFramework(Class<T> listClass, Class<E> elementClass) {
        try {
            Constructor<T> constructor = listClass.getDeclaredConstructor();
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

    @SuppressWarnings("unchecked")
    public void sortList() {
        list.sort((o1, o2) -> ((Comparable<E>) o1).compareTo(o2));
    }

    public List<E> getList() {
        return list;
    }

    public static void main(String[] args) {
        BinarySearchFramework<ArrayList<String>, String> arrayListFramework =
                new BinarySearchFramework<>(ArrayList.class, String.class);
        arrayListFramework.addElement("apple");
        arrayListFramework.addElement("banana");
        arrayListFramework.addElement("cherry");
        arrayListFramework.shuffleList();
        arrayListFramework.sortList();
        System.out.println(arrayListFramework.getList());

        BinarySearchFramework<LinkedList<String>, String> linkedListFramework =
                new BinarySearchFramework<>(LinkedList.class, String.class);
        linkedListFramework.addElement("apple");
        linkedListFramework.addElement("banana");
        linkedListFramework.addElement("cherry");
        linkedListFramework.shuffleList();
        linkedListFramework.sortList();
        System.out.println(linkedListFramework.getList());
    }
}
