package lista;

import java.util.NoSuchElementException;

public interface List<E> {

    E first() throws NoSuchElementException;

    E last() throws NoSuchElementException;

    E before(int i) throws IndexOutOfBoundsException;

    E after(int i) throws IndexOutOfBoundsException;

    // Inserções e Remoções
    void insertFirst(E e);

    void insertLast(E e);

    void insertBefore(int i, E e) throws IndexOutOfBoundsException;

    void insertAfter(int i, E e) throws IndexOutOfBoundsException;

    E remove(int i) throws IndexOutOfBoundsException;

    // Utilitários
    void swapElements(int i, int j) throws IndexOutOfBoundsException;

    int size();

    boolean isEmpty();
}