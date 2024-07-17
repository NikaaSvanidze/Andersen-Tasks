package ArrayList;

import java.util.Arrays;

public class MyArrayList {
    //HomeWork6-Task1

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.array = new Object[initialCapacity];
        this.size = 0;
    }

    public void put(Object element) {
        if (size == array.length) {
            resize();
        }
        array[size++] = element;
    }


    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        return array[index];
    }


    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null; // clear the last element
        size--;
    }


    private void resize() {
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
