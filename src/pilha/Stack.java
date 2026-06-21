package pilha;

public interface Stack<E> {
    int size();

    boolean isEmpty();

    E top() throws EmptyStackException;

    void push(E e);

    E pop() throws EmptyStackException;
}
