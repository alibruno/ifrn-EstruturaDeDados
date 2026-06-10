package heap;

import filaprioridade.Entry;
import filaprioridade.PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;

public class BinaryArrayHeap<K, V> implements PriorityQueue<K, V> {
    private Item<K, V>[] elements;
    private int size;
    private int capacity;
    private final Comparator<? super K> comparator;

    @SuppressWarnings("unchecked")
    public BinaryArrayHeap(int capacity, Comparator<? super K> comparator) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        this.elements = (Item<K, V>[]) new Item[capacity + 1]; // index 0 is empty
        this.capacity = capacity;
        this.size = 0;
        this.comparator = comparator;
    }

    public BinaryArrayHeap(Comparator<? super K> comparator) {
        this(11, comparator);
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        if (size == capacity) {
            resize();
        }
        Item<K, V> item = new Item<>(key, value);
        elements[++size] = item;
        upheap(size);
        return item;
    }

    @Override
    public Entry<K, V> removeMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        // The Entry of the root is stored
        Entry<K, V> min = elements[1];
        // The last element of the Heap is taken and placed in the root
        elements[1] = elements[size];

        elements[size] = null;
        size--;

        // If the heap is not empty, it is sorted by descending the element from the root
        if (size > 0) {
            downheap(1);
        }

        return min;
    }

    private void downheap(int i) {
        // The loop iterates as long as the current node has at least one child (the one on the left)
        while (2 * i <= size) {
            int smallerChild = getSmallerChild(i);
            int cmp = comparator.compare(elements[i].key(), elements[smallerChild].key());

            // If actualNode <= smallerChild, the Min-Heap order is correct
            if (cmp <= 0) {
                break;
            }
            swap(i, smallerChild);
            i = smallerChild;
        }
    }

    private int getSmallerChild(int i) {
        int leftChild = 2 * i;
        int rightChild = 2 * i + 1;

        int smallerChild = leftChild;

        if (rightChild <= size) {
            int cmpChildren = comparator.compare(elements[rightChild].key(), elements[leftChild].key());
            if (cmpChildren < 0) {
                smallerChild = rightChild;
            }
        }
        return smallerChild;
    }

    @Override
    public Entry<K, V> min() {
        if (isEmpty()) {
            return null;
        }
        // min element -> root value (Min-Heap)
        return elements[1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        Item<K, V>[] newElements = (Item<K, V>[]) new Item[newCapacity + 1];

        for (int i = 1; i <= size; i++) {
            newElements[i] = elements[i];
        }

        this.elements = newElements;
        this.capacity = newCapacity;
    }

    private void upheap(int i) {
        while (i > 1) {
            int parent = i / 2;
            int cmp = comparator.compare(elements[i].key(), elements[parent].key());

            // actualNode < parentNode
            if (cmp < 0) {
                swap(i, parent);
                i = parent; // 'i' must be updated for the next iteration
            } else {
                break; // The order of Min-Heap has been restored
            }
        }
    }

    private void swap(int i, int j) {
        Item<K, V> temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private record Item<K, V>(K key, V value) implements Entry<K, V> {
    }

    @Override
    public String toString() {
        return "BinaryArrayHeap{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}