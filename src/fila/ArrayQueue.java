package fila;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {
    private E[] elements;
    private int head, tail, capacity;
    private final int increment;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity, int increment) {
        this.elements = (E[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity; //Tamanho
        this.increment = increment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void enqueue(E e) {
        if (size() == capacity - 1) { // encheu coleguinha
            int newCapacity = increment == 0 ? capacity * 2 : capacity + increment;
            E[] newArray = (E[]) new Object[newCapacity];
            int tempHead = head;

            for (int i = 0; i < size(); i++) {
                newArray[i] = elements[tempHead];
                tempHead = (tempHead + 1) % capacity;
            }

            tail = size(); // definir o novo final
            head = 0; // definir o novo inicio
            capacity = newCapacity;
            elements = newArray;
        }
        elements[tail] = e;
        tail = (tail + 1) % capacity;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        E temp = elements[head];
        head = (head + 1) % capacity;
        return temp;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return elements[head];
    }

    @Override
    public int size() {
        return (capacity - head + tail) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public String toString() {
        return "FilaArray{" +
                "elements=" + Arrays.toString(elements) +
                ", head=" + head +
                ", tail=" + tail +
                ", capacity=" + capacity +
                ", increment=" + increment +
                '}';
    }
}