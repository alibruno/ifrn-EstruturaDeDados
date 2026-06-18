package filaprioridade;

public interface PriorityQueue<K, V> {
    int size();

    boolean isEmpty();

    Entry<K, V> min();

    Entry<K, V> insert(K key, V value);

    Entry<K, V> removeMin();

}
