package sequencia;

public class LinkedSequence<E> implements Sequence<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedSequence() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.setNext(tail);
        size = 0;
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
    public Node<E> atRank(int r) {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Invalid rank: " + r);
        }

        Node<E> node;
        if (r <= size() / 2) {
            node = head.getNext();
            for (int i = 0; i < r; i++) {
                node = node.getNext();
            }
        } else {
            node = tail.getPrev();
            for (int i = 0; i < size() - r - 1; i++) {
                node = node.getPrev();
            }
        }
        return node;
    }

    @Override
    public int rankOf(Node<E> p) {
        if (p == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        Node<E> current = head.getNext();
        int r = 0;
        while (current != p && current != tail) {
            current = current.getNext();
            r++;
        }
        if (current == tail) {
            throw new IllegalArgumentException("Node not found in the sequence");
        }
        return r;
    }

    @Override
    public Node<E> first() {
        return head.getNext();
    }

    @Override
    public Node<E> last() {
        return tail.getPrev();
    }

    @Override
    public Node<E> before(Node<E> p) {
        return p.getPrev();
    }

    @Override
    public Node<E> after(Node<E> p) {
        return p.getNext();
    }

    @Override
    public Node<E> insertBefore(Node<E> p, E e) {
        if (p == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        Node<E> newNode = new Node<>(e, p.getPrev(), p);
        p.getPrev().setNext(newNode);
        p.setPrev(newNode);
        size++;
        return newNode;
    }

    @Override
    public Node<E> insertAfter(Node<E> p, E e) {
        if (p == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        Node<E> newNode = new Node<>(e, p, p.getNext());
        p.getNext().setPrev(newNode);
        p.setNext(newNode);
        size++;
        return newNode;
    }

    @Override
    public Node<E> insertFirst(E e) {
        return insertAfter(head, e);
    }

    @Override
    public Node<E> insertLast(E e) {
        return insertBefore(tail, e);
    }

    @Override
    public E remove(Node<E> p) {
        if (p == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        Node<E> prev = p.getPrev();
        Node<E> next = p.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;

        E element = p.getElement();
        p.setNext(null);
        p.setPrev(null);
        return element;
    }

    @Override
    public E replaceElement(Node<E> p, E e) {
        if (p == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        E old = p.getElement();
        p.setElement(e);
        return old;
    }

    @Override
    public void swapElements(Node<E> p, Node<E> q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        E temp = p.getElement();
        p.setElement(q.getElement());
        q.setElement(temp);
    }

    @Override
    public E elemAtRank(int r) {
        return atRank(r).getElement();
    }

    @Override
    public E replaceAtRank(int r, E e) {
        return replaceElement(atRank(r), e);
    }

    @Override
    public void insertAtRank(int r, E e) {
        if (r < 0 || r > size) {
            throw new IndexOutOfBoundsException("Invalid rank: " + r);
        }
        if (r == size) {
            insertLast(e);
        } else {
            insertBefore(atRank(r), e);
        }
    }

    @Override
    public E removeAtRank(int r) {
        return remove(atRank(r));
    }
}
