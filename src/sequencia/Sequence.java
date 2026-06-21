package sequencia;

public interface Sequence<E> {
    int size();

    boolean isEmpty();

    Node<E> first();

    Node<E> last();

    Node<E> before(Node<E> p);

    Node<E> after(Node<E> p);

    E replaceElement(Node<E> p, E e);

    void swapElements(Node<E> p, Node<E> q);

    Node<E> insertBefore(Node<E> p, E e);

    Node<E> insertAfter(Node<E> p, E e);

    Node<E> insertFirst(E e);

    Node<E> insertLast(E e);

    E remove(Node<E> p);

    E elemAtRank(int r);

    E replaceAtRank(int r, E e);

    void insertAtRank(int r, E e);

    E removeAtRank(int r);

    Node<E> atRank(int r);

    int rankOf(Node<E> p);
}
