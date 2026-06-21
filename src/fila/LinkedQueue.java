package fila;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // No só poderá ser acessado dentro dessa estrutura
    // No é estático pois não precisa de uma instância da fila para existir.
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }


    @Override
    public void enqueue(E e) {
        Node<E> node = new Node<>(e);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            throw new EmptyQueueException("Queue is empty");
        }
        E temp = head.element;
        if (size != 1) {
            head = head.next;
        } else {
            head = null;
            tail = null;
        }
        size--;
        return temp;
    }

    @Override
    public E first() {
        if (head == null) {
            throw new EmptyQueueException("Queue is empty");
        }
        return head.element;
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
        return "LinkedQueue{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                ", No{" + allNodeElements() + "}" +
                '}';
    }

    private String allNodeElements() {
        if (head == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Node<E> node = head;
        while (node != null) {
            sb.append(node.element).append(',').append(' ');
            node = node.next;
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
