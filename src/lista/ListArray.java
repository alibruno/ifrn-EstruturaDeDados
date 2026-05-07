package lista;

import java.util.Arrays;

public class ListArray<T> implements List<T> {
    private T[] elements;
    private int head, tail, capacity;

    @SuppressWarnings("unchecked")
    public ListArray(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity;
    }

    private int nextIndex(int currentIndex) {
        return (currentIndex + 1) % capacity;
    }

    private int previousIndex(int currentIndex) {
        return (currentIndex - 1 + capacity) % capacity;
    }

    private int physicalIndex(int r) {
        return (head + r) % capacity;
    }

    private int indexOfElement(T e) {
        int current = head;
        for (int i = 0; i < size(); i++) {
            if (elements[current].equals(e)) {
                return current;
            }
            current = nextIndex(current);
        }
        throw new IllegalArgumentException("Element '" + e + "' not found");
    }

    @Override
    public boolean isFirst(T e) {
        if (isEmpty()) {
            return false;
        }
        return elements[head].equals(e);
    }

    @Override
    public boolean isLast(T e) {
        if (isEmpty()) {
            return false;
        }
        return elements[previousIndex(tail)].equals(e);
    }

    @Override
    public T first() {
        if (isEmpty()){
            throw new EmptyListException("List is empty");
        }
        return elements[head];
    }

    @Override
    public T last() {
        if (isEmpty()){
            throw new EmptyListException("List is empty");
        }
        return elements[previousIndex(tail)];
    }

    @Override
    public T before(T e) {
        int current = nextIndex(head);
        for (int i = 1; i < size(); i++) {
            if (elements[current].equals(e)) {
                return elements[previousIndex(current)];
            }
            current = nextIndex(current);
        }
        throw new IllegalArgumentException("Element '" + e + "' not found, or it's the first one");
    }

    @Override
    public T after(T e) {
        int current = head;
        for (int i = 0; i < size() - 1; i++) {
            if (elements[current].equals(e)) {
                return elements[nextIndex(current)];
            }
            current = nextIndex(current);
        }
        throw new IllegalArgumentException("Element '" + e + "' not found, or it's the last one");
    }

    @Override
    public void replaceElement(T n, T e) {
        int targetIndex = indexOfElement(e);
        elements[targetIndex] = n;
    }

    @Override
    public void swapElements(T n, T q) {
        int targetIndexF = indexOfElement(n);
        int targetIndexS = indexOfElement(q);
        T temp = elements[targetIndexF];
        elements[targetIndexF] = elements[targetIndexS];
        elements[targetIndexS] = temp;
    }

    @Override
    public void insertBefore(int n, T e) {
        if (n == 0) {
            insertFirst(e);
            return;
        }
        if (n < 0 || n >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + n + ", Size: " + size());
        }
        // O(n)
        verifyIsArrayFull();
        copyAndInsertAt(n, e);
    }

    @Override
    public void insertAfter(int n, T e) {
        if (n == size() - 1) {
            insertLast(e);
            return;
        }
        if (n < 0 || n >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + n + ", Size: " + size());
        }
        // O(n)
        verifyIsArrayFull();
        copyAndInsertAt(n + 1, e);
    }

    @Override
    public void insertFirst(T e) {
        verifyIsArrayFull();
        head = previousIndex(head);
        elements[head] = e;
    }

    @Override
    public void insertLast(T e) {
        verifyIsArrayFull();
        elements[tail] = e;
        tail = nextIndex(tail);
    }

    @Override
    public T remove(int n) {
        if (isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        if (n < 0 || n >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + n + ", Size: " + size());
        }
        T removed;
        // if() e else if() -> O(1); else -> O(n)
        if (n == 0) {
            removed = elements[head];
            elements[head] = null;
            head = nextIndex(head);
        } else if (n == size() - 1) {
            tail = previousIndex(tail);
            removed = elements[tail];
            elements[tail] = null;
        } else {
            removed = elements[physicalIndex(n)];
            copyAndRemoveAt(n);
        }
        return removed;
    }

    @Override
    public int size() {
        return (capacity - head + tail) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    private void verifyIsArrayFull() {
        // O(n)
        if (size() == capacity - 1) {
            grow();
        }
    }

    @SuppressWarnings("unchecked")
    private T[] createNewArray(int capacity) {
        return (T[]) new Object[capacity];
    }

    private void grow() {
        int newCapacity = capacity * 2;
        T[] newArray = createNewArray(newCapacity);
        int tempHead = head;

        for (int i = 0; i < size(); i++) {
            newArray[i] = elements[tempHead];
            tempHead = nextIndex(tempHead);
        }

        tail = size(); // definir o novo final
        head = 0; // definir o novo inicio
        capacity = newCapacity;
        elements = newArray;
    }

    private void copyAndInsertAt(int r, T e) {
        T[] newArray = createNewArray(capacity);
        int tempHead = head;

        for (int i = 0; i < r; i++) {
            newArray[i] = elements[tempHead];
            tempHead = nextIndex(tempHead);
        }

        newArray[r] = e;

        for (int i = r; i < size(); i++) {
            newArray[i + 1] = elements[tempHead];
            tempHead = nextIndex(tempHead);
        }

        tail = size() + 1; // definir o novo final
        head = 0; // definir o novo inicio
        elements = newArray;
    }

    private void copyAndRemoveAt(int r) {
        T[] newArray = createNewArray(capacity);
        int tempHead = head;

        for (int i = 0; i < r; i++) {
            newArray[i] = elements[tempHead];
            tempHead = nextIndex(tempHead);
        }

        tempHead = nextIndex(tempHead);

        for (int i = r; i < size() - 1; i++) {
            newArray[i] = elements[tempHead];
            tempHead = nextIndex(tempHead);
        }

        tail = size() - 1; // definir o novo final
        head = 0; // definir o novo inicio
        elements = newArray;
    }

    @Override
    public String toString() {
        return "VectorArray{" +
                "elements=" + Arrays.toString(elements) +
                ", head=" + first() +
                ", tail=" + last() +
                ", size=" + size() +
                '}';
    }

}
