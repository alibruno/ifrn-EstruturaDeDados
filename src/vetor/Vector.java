package vetor;

public interface Vector<E> {
    void insertAtRank(int r, E e);

    E replaceAtRank(int r, E e);

    E removeAtRank(int r);

    E elemAtRank(int r);

    int size();

    boolean isEmpty();
}
