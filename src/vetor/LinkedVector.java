package vetor;

public class LinkedVector<E> implements Vector<E> {
    // head e tail -> nós sentinelas
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedVector() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E element) {
            this.element = element;
        }
    }

    // Busca baseando-se na proximidade dos extremos do começo e final
    private Node<E> nodeAt(int r) {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Index: " + r + ", Size: " + size);
        }
        Node<E> current;
        if (r < size / 2) {
            current = head.next;
            for (int i = 0; i < r; i++) {
                current = current.next;
            }
        } else {
            current = tail.prev;
            for (int i = size - 1; i > r; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public void insertAtRank(int r, E e) {
        if (r < 0 || r > size) {
            throw new IndexOutOfBoundsException("Index: " + r + ", Size: " + size);
        }

        Node<E> current;
        if (r == size) {
            current = tail; // Inserir no final -> antes do tail
        } else {
            current = nodeAt(r);
        }

        Node<E> newNode = new Node<>(e);
        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;

        size++;
    }

    @Override
    public E replaceAtRank(int r, E e) {
        Node<E> target = nodeAt(r);

        E replaced = target.element;
        target.element = e;

        return replaced;
    }

    @Override
    public E removeAtRank(int r) {
        if (isEmpty()) {
            throw new EmptyVectorException("Vector is empty");
        }
        Node<E> target = nodeAt(r);
        E removed = target.element;

        target.prev.next = target.next;
        target.next.prev = target.prev;
        size--;

        return removed;
    }

    @Override
    public E elemAtRank(int r) {
        return nodeAt(r).element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        return "LinkedVector{" +
                "Nodes=" + allNodes() +
                ", size=" + size +
                '}';
    }

    private String allNodes() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head.next;

        while (current != tail) {
            sb.append(current.element);
            if (current.next != tail) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}