package fila.reverse;

import fila.EmptyQueueException;

import java.util.Arrays;

public class QueueReverseArray implements QueueReverse {
    private Object[] elements;
    private int head, tail, capacity;
    private final int increment;
    private boolean isReversed;

    public QueueReverseArray(int capacity, int increment) {
        this.elements = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity;
        this.increment = increment;
        this.isReversed = false;
    }

    // Método auxiliar - calcular o próximo índice baseado na direção
    // O capacity é somado antes do módulo para evitar números negativos
    private int nextIndex(int currentIndex) {
        int step = isReversed ? -1 : 1;
        return (currentIndex + step + capacity) % capacity;
    }

    // Método auxiliar - andar para trás (usado no reverse)
    // Oposto do nextIndex
    private int previousIndex(int currentIndex) {
        int step = isReversed ? -1 : 1;
        return (currentIndex - step + capacity) % capacity;
    }

    @Override
    public void enqueue(Object o) {
        if (size() == capacity - 1) {
            increaseCapacity();
        }
        elements[tail] = o;
        tail = nextIndex(tail);
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        Object temp = elements[head];
        head = nextIndex(head);

        if (size() > 0 && size() <= capacity / 3) {
            reduceCapacity();
        }
        return temp;
    }


    @Override
    public void reverse() {
        if (isEmpty()) {
            return;
        }
        int newHead = previousIndex(tail);
        int newTail = previousIndex(head);
        this.head = newHead;
        this.tail = newTail;
        this.isReversed = !isReversed;
    }

    @Override
    public Object first() {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return elements[head];
    }

    @Override
    public int size() {
        if (isReversed) {
            return (head - tail + capacity) % capacity;
        }
        return (tail - head + capacity) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    private int calculateNewCapacity() {
        return increment == 0 ? capacity * 2 : capacity + increment;
    }

    // Inserção no modo invertido
    //    Array: [ A, B, C, null ] (está revertido)
    //    Array: [ A, B, C, D ] (encheu, little college)
    //    Ordem: C -> B -> A -> D
    // increaseCapacity()
    //    faz a cópia do array antigo seguindo a ordem lógica - De trás para frente
    //    Novo array: [ C, B, A, D, null, null, null, null ]
    //    Dados já salvos na ordem invertida - Há um reset no isReversed: isReversed = false.
    //
    // dequeue() => C.
    // enqueue(E) => C -> B -> A -> D -> E

    private void increaseCapacity() {
        int newCapacity = calculateNewCapacity();
        Object[] newArray = new Object[newCapacity];

        int currentSize = size();
        int currentPointer = head;

        // Iteração normal
        for (int i = 0; i < currentSize; i++) {
            newArray[i] = elements[currentPointer];
            currentPointer = nextIndex(currentPointer); // Anda conforme a direção
        }

        // Reset para o modo 'normal'
        this.elements = newArray;
        this.head = 0;
        this.tail = currentSize;
        this.capacity = newCapacity;
        this.isReversed = false;
    }

    private void reduceCapacity() {
        int minCapacity = 8;
        int newCapacity = Math.max(minCapacity, capacity / 2);

        Object[] newArray = new Object[newCapacity];

        int currentSize = size();
        int currentPointer = head;

        for (int i = 0; i < currentSize; i++) {
            newArray[i] = elements[currentPointer];
            currentPointer = nextIndex(currentPointer);
        }

        this.elements = newArray;
        this.head = 0;
        this.tail = currentSize;
        this.capacity = newCapacity;
        this.isReversed = false;
    }

    @Override
    public String toString() {
        return "FilaReverseArray{" +
                "elements=" + Arrays.toString(elements) +
                ", head=" + head +
                ", tail=" + tail +
                ", size=" + size() +
                ", isReversed=" + isReversed +
                '}';
    }
}