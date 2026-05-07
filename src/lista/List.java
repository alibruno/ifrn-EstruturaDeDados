package lista;

public interface List<T> {
    boolean isFirst(T e);

    boolean isLast(T e);

    T first();

    T last();

    T before(T e);

    T after(T e);

    void replaceElement(T n, T e);

    void swapElements(T n, T q);

    void insertBefore(int n, T e);

    void insertAfter(int n, T e);

    void insertFirst(T e);

    void insertLast(T e);

    T remove(int n);

    int size();

    boolean isEmpty();
}
