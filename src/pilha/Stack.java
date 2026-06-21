package pilha;

public interface Stack<E> {
    int size();

    boolean isEmpty();

    E top();

    void push(E e);

    E pop();
}
