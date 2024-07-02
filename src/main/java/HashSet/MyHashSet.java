package HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyHashSet {
    //HomeWork 6 Task-2
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;

    private List<Object>[] buckets;
    private int size;

    public MyHashSet() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashSet(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.buckets = new ArrayList[initialCapacity];
        this.size = 0;
    }

    public void put(Object element) {
        if (loadFactorExceeded()) {
            resize();
        }
        int index = getIndex(element);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }
        if (!contains(element)) {
            buckets[index].add(element);
            size++;
        }
    }

    public boolean contains(Object element) {
        int index = getIndex(element);
        if (buckets[index] != null) {
            for (Object item : buckets[index]) {
                if (item.equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void delete(Object element) {
        int index = getIndex(element);
        if (buckets[index] != null) {
            buckets[index].remove(element);
            size--;
        }
    }

    public Iterator<Object> iterator() {
        return new HashSetIterator();
    }

    private int getIndex(Object element) {
        return Math.abs(element.hashCode() % buckets.length);
    }

    private boolean loadFactorExceeded() {
        return (double) size / buckets.length > LOAD_FACTOR;
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        List<Object>[] newBuckets = new ArrayList[newCapacity];
        for (List<Object> bucket : buckets) {
            if (bucket != null) {
                for (Object element : bucket) {
                    int newIndex = Math.abs(element.hashCode() % newCapacity);
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new ArrayList<>();
                    }
                    newBuckets[newIndex].add(element);
                }
            }
        }
        buckets = newBuckets;
    }

    private class HashSetIterator implements Iterator<Object> {
        private int bucketIndex;
        private int listIndex;

        public HashSetIterator() {
            bucketIndex = 0;
            listIndex = -1;
            findNextElement();
        }

        @Override
        public boolean hasNext() {
            while (bucketIndex < buckets.length && (buckets[bucketIndex] == null || listIndex >= buckets[bucketIndex].size() - 1)) {
                bucketIndex++;
                listIndex = -1;
            }
            return bucketIndex < buckets.length && listIndex < buckets[bucketIndex].size() - 1;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements to iterate");
            }
            listIndex++;
            return buckets[bucketIndex].get(listIndex);
        }

        private void findNextElement() {
            while (bucketIndex < buckets.length && (buckets[bucketIndex] == null || buckets[bucketIndex].isEmpty())) {
                bucketIndex++;
                listIndex = -1;
            }
        }
    }
}
