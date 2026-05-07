package lista;

public class ListLinked<T> implements List<T> {
    // head e tail -> nós sentinelas
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public ListLinked() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> findNode(T e) {
        Node<T> current = head.next;
        while (current != tail) {
            if (current.element.equals(e)) {
                return current;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("Element '" + e + "' not found");
    }

    // Busca baseando-se na proximidade dos extremos do começo e final
    private Node<T> findNode(int n) {
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + size);
        }
        Node<T> current;
        if (n < size / 2) {
            current = head.next;
            for (int i = 0; i < n; i++) {
                current = current.next;
            }
        } else {
            current = tail.prev;
            for (int i = size - 1; i > n; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public boolean isFirst(T e) {
        if (isEmpty()) {
            return false;
        }
        return head.next.element.equals(e);
    }

    @Override
    public boolean isLast(T e) {
        if (isEmpty()) {
            return false;
        }
        return tail.prev.element.equals(e);
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        return head.next.element;
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        return tail.prev.element;
    }

    @Override
    public T before(T e) {
        Node<T> node = findNode(e);
        if (node.prev == head) {
            throw new IllegalArgumentException("Element '" + e + "' is the first one, has no before");
        }
        return node.prev.element;
    }

    @Override
    public T after(T e) {
        Node<T> node = findNode(e);
        if (node.next == tail) {
            throw new IllegalArgumentException("Element '" + e + "' is the last one, has no after");
        }
        return node.next.element;
    }

    @Override
    public void replaceElement(T n, T e) {
        Node<T> node = findNode(e);
        node.element = n;
    }

    @Override
    public void swapElements(T n, T q) {
        Node<T> nodeF = findNode(n);
        Node<T> nodeS = findNode(q);
        T temp = nodeF.element;
        nodeF.element = nodeS.element;
        nodeS.element = temp;
    }

    @Override
    public void insertBefore(int n, T e) {
        Node<T> target = findNode(n);
        Node<T> newNode = new Node<>(e);

        newNode.prev = target.prev;
        newNode.next = target;
        target.prev.next = newNode;
        target.prev = newNode;

        size++;
    }

    @Override
    public void insertAfter(int n, T e) {
        Node<T> target = findNode(n);
        Node<T> newNode = new Node<>(e);

        newNode.prev = target;
        newNode.next = target.next;
        target.next.prev = newNode;
        target.next = newNode;

        size++;
    }

    @Override
    public void insertFirst(T e) {
        Node<T> newNode = new Node<>(e);
        newNode.next = head.next;
        newNode.prev = head;

        head.next.prev = newNode;
        head.next = newNode;

        size++;
    }

    @Override
    public void insertLast(T e) {
        Node<T> newNode = new Node<>(e);
        newNode.prev = tail.prev;
        newNode.next = tail;

        tail.prev.next = newNode;
        tail.prev = newNode;

        size++;
    }

    @Override
    public T remove(int n) {
        if (isEmpty()) {
            throw new EmptyListException("Vector is empty");
        }
        Node<T> target = findNode(n);
        T removed = target.element;

        target.prev.next = target.next;
        target.next.prev = target.prev;

        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "ListLinked{" +
                "Nodes=" + allNodes() +
                ", size=" + size +
                '}';
    }

    private String allNodes() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head.next;

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