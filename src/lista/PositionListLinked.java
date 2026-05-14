package lista;

public class PositionListLinked<E> implements PositionList<E> {
    // Nós sentinelas
    private final Node<E> head;
    private final Node<E> tail;
    private int size;

    public PositionListLinked() {
        this.head = new Node<>(null, null, null);
        this.tail = new Node<>(head, null, null);
        this.head.next = this.tail;
        this.size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> findNode(E e) {
        Node<E> current = head.next;
        while (current != tail) {
            if (current.element.equals(e)) {
                return current;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("Element '" + e + "' not found");
    }

    // Busca baseando-se na proximidade dos extremos do começo e final
    private Node<E> findNode(int n) {
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + size);
        }
        Node<E> current;
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
    public boolean isFirst(E e) {
        if (isEmpty()) {
            return false;
        }
        return head.next.element.equals(e);
    }

    @Override
    public boolean isLast(E e) {
        if (isEmpty()) {
            return false;
        }
        return tail.prev.element.equals(e);
    }

    @Override
    public Node<E> first() {
        if (isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        return head.next;
    }

    @Override
    public E last() {
        if (isEmpty()) {
            throw new EmptyListException("List is empty");
        }
        return tail.prev.element;
    }

    @Override
    public E before(E e) {
        Node<E> node = findNode(e);
        if (node.prev == head) {
            throw new IllegalArgumentException("Element '" + e + "' is the first one, has no before");
        }
        return node.prev.element;
    }

    @Override
    public E after(E e) {
        Node<E> node = findNode(e);
        if (node.next == tail) {
            throw new IllegalArgumentException("Element '" + e + "' is the last one, has no after");
        }
        return node.next.element;
    }

    @Override
    public void replaceElement(E n, E e) {
        Node<E> node = findNode(e);
        node.element = n;
    }

    @Override
    public void swapElements(E n, E q) {
        Node<E> nodeF = findNode(n);
        Node<E> nodeS = findNode(q);
        E temp = nodeF.element;
        nodeF.element = nodeS.element;
        nodeS.element = temp;
    }

    @Override
    public void insertBefore(int n, E e) {
        Node<E> target = findNode(n);
        Node<E> newNode = new Node<>(e);

        newNode.prev = target.prev;
        newNode.next = target;
        target.prev.next = newNode;
        target.prev = newNode;

        size++;
    }

    @Override
    public void insertAfter(int n, E e) {
        Node<E> target = findNode(n);
        Node<E> newNode = new Node<>(e);

        newNode.prev = target;
        newNode.next = target.next;
        target.next.prev = newNode;
        target.next = newNode;

        size++;
    }

    @Override
    public void insertFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head.next;
        newNode.prev = head;

        head.next.prev = newNode;
        head.next = newNode;

        size++;
    }

    @Override
    public void insertLast(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.prev = tail.prev;
        newNode.next = tail;

        tail.prev.next = newNode;
        tail.prev = newNode;

        size++;
    }

    @Override
    public E remove(int n) {
        if (isEmpty()) {
            throw new EmptyListException("Vector is empty");
        }
        Node<E> target = findNode(n);
        E removed = target.element;

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