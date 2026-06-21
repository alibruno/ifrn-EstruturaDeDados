package pilha;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {
    private E[] data;
    private int index;
    private int capacity;
    private int increment;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity, int increment) {
        this.index = -1;
        this.capacity = capacity;
        this.increment = 0;
        if (increment > 0)
            this.increment = increment;
        this.data = (E[]) new Object[capacity];
    }

    public void push(E e) {
        if (index == capacity - 1) {
            increaseCapacity();
        }
        data[++index] = e;
    }

    public E pop() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");
        return data[index--];
    }

    public E top() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException("Stack is empty");
        return data[index];
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public int size() {
        return index + 1;
    }

    @SuppressWarnings("unchecked")
    private void increaseCapacity() {
        capacity = calculateNewCapacity();
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < data.length; i++)
            newData[i] = data[i];
        data = newData;
    }

    private int calculateNewCapacity() {
        return increment == 0 ? capacity * 2 : capacity + increment;
    }

    @Override
    public String toString() {
        return "ArrayStack{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
