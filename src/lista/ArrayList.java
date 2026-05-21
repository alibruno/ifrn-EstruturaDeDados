package lista;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    private E[] elements;
    private int head;
    private int size;
    private int capacity;
    // tail = size - 1

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.elements = (E[]) new Object[capacity];
        this.head = 0;
        this.size = 0;
    }

    // MÉTODOS AUXILIARES

    private int physicalIndex(int i) {
        return (head + i) % capacity;
    }

    private void checkIndex(int i, int maxAllowed) {
        if (i < 0 || i > maxAllowed) {
            throw new IndexOutOfBoundsException("Índice inválido: " + i + ", Tamanho: " + size);
        }
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = capacity * 2;
        E[] newArray = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = elements[physicalIndex(i)];
        }

        this.elements = newArray;
        this.capacity = newCapacity;
        this.head = 0;
    }

    private void addAt(int i, E e) {
        checkIndex(i, size); // Permite 'i' igual a 'size' para inserir no final
        if (size == capacity) {
            grow();
        }

        if (i == 0) { // Inserção no início (O(1))
            head = (head - 1 + capacity) % capacity;
            elements[head] = e;
        } else if (i == size) { // Inserção no final (O(1))
            elements[physicalIndex(size)] = e;
        } else { // Inserção no meio com shift (O(n))
            for (int k = size - 1; k >= i; k--) {
                int current = physicalIndex(k);
                int next = physicalIndex(k + 1);
                elements[next] = elements[current]; // Empurra para a direita
            }
            elements[physicalIndex(i)] = e;
        }
        size++;
    }

    // MÉTODOS DA INTERFACE

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E first() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return elements[head];
    }

    @Override
    public E last() {
        if (isEmpty()) throw new NoSuchElementException("List is empty");
        return elements[physicalIndex(size - 1)];
    }

    // Navegação baseada em índice O(1)
    @Override
    public E before(int i) {
        checkIndex(i, size - 1);
        if (i == 0) return null; // Não há elemento antes do primeiro
        return elements[physicalIndex(i - 1)];
    }

    @Override
    public E after(int i) {
        checkIndex(i, size - 1);
        if (i == size - 1) return null; // Não há elemento depois do último
        return elements[physicalIndex(i + 1)];
    }

    // Os métodos de inserção apenas repassam o trabalho para a lógica central
    @Override
    public void insertFirst(E e) {
        addAt(0, e);
    }

    @Override
    public void insertLast(E e) {
        addAt(size, e);
    }

    @Override
    public void insertBefore(int i, E e) {
        // Inserir antes do índice 'i' é colocar o elemento na própria posição 'i'
        addAt(i, e);
    }

    @Override
    public void insertAfter(int i, E e) {
        // Inserir depois do índice 'i' é colocar o elemento na posição 'i + 1'
        addAt(i + 1, e);
    }

    @Override
    public void swapElements(int i, int j) {
        checkIndex(i, size - 1);
        checkIndex(j, size - 1);
        int physI = physicalIndex(i);
        int physJ = physicalIndex(j);

        E temp = elements[physI];
        elements[physI] = elements[physJ];
        elements[physJ] = temp;
    }

    @Override
    public E remove(int i) {
        checkIndex(i, size - 1);
        if (isEmpty()) throw new NoSuchElementException("List is empty");

        E removedElement = elements[physicalIndex(i)];

        if (i == 0) {
            elements[head] = null;
            head = (head + 1) % capacity;
        } else if (i == size - 1) {
            elements[physicalIndex(size - 1)] = null;
        } else {
            // Shift para cobrir o buraco
            for (int k = i; k < size - 1; k++) {
                int current = physicalIndex(k);
                int next = physicalIndex(k + 1);
                elements[current] = elements[next]; // Puxa para a esquerda
            }
            elements[physicalIndex(size - 1)] = null; // Limpa o último slot duplicado
        }

        size--;
        return removedElement;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "ListArray{empty}";
        return "ListArray{" +
                "elements=" + Arrays.toString(elements) +
                ", headIndex=" + head +
                ", first=" + first() +
                ", last=" + last() +
                ", size=" + size +
                '}';
    }
}