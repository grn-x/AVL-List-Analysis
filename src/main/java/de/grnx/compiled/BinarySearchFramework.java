package de.grnx.compiled;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
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


    public BinarySearchFramework() {
        try {

            Class<T> persistentClass = (Class<T>)
                    ((ParameterizedType)getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0]; //Holy fuck reflection magic saving the day

            Constructor<T> constructor = persistentClass.getDeclaredConstructor();
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
        BinarySearchFramework<ArrayList<String>, String> arrayListFramework = new BinarySearchFramework<>();
        arrayListFramework.addElement("apple");
        arrayListFramework.addElement("banana");
        arrayListFramework.addElement("cherry");
        arrayListFramework.shuffleList();
        arrayListFramework.sortList();
        System.out.println(arrayListFramework.getList());

        BinarySearchFramework<LinkedList<String>, String> linkedListFramework = new BinarySearchFramework<>();
        linkedListFramework.addElement("apple");
        linkedListFramework.addElement("banana");
        linkedListFramework.addElement("cherry");
        linkedListFramework.shuffleList();
        linkedListFramework.sortList();
        System.out.println(linkedListFramework.getList());
    }
}