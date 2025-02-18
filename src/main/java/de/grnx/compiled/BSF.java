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
public class BSF<T extends List<E>, E extends Comparable> {
    private T list;

    public BSF(Class<? extends T> clazz) {
        try {
            Constructor<? extends T> constructor = clazz.getDeclaredConstructor();
            this.list = constructor.newInstance();
            if(!(list instanceof RandomAccess)){
                System.err.println("Warning: " + list.getClass().getTypeName() +" is not RandomAccess");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate list", e);
        }
    }

    public void addElement(E element) {
        list.add(element);
    }

    public void addElement(int index, E element) {
        list.add(index, element);
    }

    public void insertElement(E element) {
        int index = searchForInsertion(element);
        list.add(index, element);
    }

    /*public void insertElementWOduplicate(E element) {
        if(list.contains(element))return;
        int index = searchForInsertion(element);
        list.add(index, element);
    }*/

    public void removeElement(E element) {
        list.remove(element);
    }

    public E get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public int search(E element) {
        if (true){ //(list instanceof RandomAccess) { //necessary? when using linked list resort to linear search, compare performance
        return binarySearch(element);
        } else {
         return linearSearch(element);
        }
    }
    private int binarySearch(E element){
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1; //unsigned shift needed pos + avoid overflow large num
            Comparable<E> midVal = (Comparable<E>) list.get(mid);
            int cmp = midVal.compareTo(element);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;

    }

    private int linearSearch(E element) {
        System.err.println("Warning: Linear search used: " + element);
        for (int i = 0; i < list.size(); i++) {
            Comparable<E> midVal = (Comparable<E>) list.get(i);
            int cmp = midVal.compareTo(element);
            if (cmp == 0) {
                return i;
            }

        }
        return -1;
    }


    public int searchForInsertion(E element) {
        if (true) { //(list instanceof RandomAccess) {
            return binarySearchForInsertion(element);
        } else {
            return linearSearchForInsertion(element);
        }
    }

    private int binarySearchForInsertion(E element) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<E> midVal = (Comparable<E>) list.get(mid);
            int cmp = midVal.compareTo(element);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // Element found
            }
        }
        return low; // Element not found, return insertion point
    }

    private int linearSearchForInsertion(E element) {
        System.err.println("Warning: Linear search used: " + element);
        for (int i = 0; i < list.size(); i++) {
            Comparable<E> midVal = (Comparable<E>) list.get(i);
            int cmp = midVal.compareTo(element);
            if (cmp == 0) {
                return i; // Element found
            } else if (cmp > 0) {
                return i; // Element not found, return insertion point
            }
        }
        return list.size(); // Element not found, return insertion point at the end
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