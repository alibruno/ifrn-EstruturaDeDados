package lista;

public interface PositionList<E> {
    boolean isFirst(Position<E> e);

    boolean isLast(Position<E> e);

    Position<E> first();

    Position<E> last();

    Position<E> before(Position<E> e);

    Position<E> after(Position<E> e);

    void replaceElement(Position<E> n, Position<E> e);

    void swapElements(Position<E> n, Position<E> q);

    void insertBefore(int n, Position<E> e);

    void insertAfter(int n, Position<E> e);

    void insertFirst(Position<E> e);

    void insertLast(Position<E> e);

    Position<E> remove(int n);

    int size();

    boolean isEmpty();
}
