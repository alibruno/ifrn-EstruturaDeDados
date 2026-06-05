package arvore.binaria;

import arvore.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
    private Node<E> root;
    private int size;

    public LinkedBinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public LinkedBinaryTree(E e) {
        this.root = new Node<>(e, null, null, null);
        this.size = 1;
    }

    @Override
    public Position<E> left(Position<E> v) throws IllegalArgumentException {
        return checkPosition(v).left; // It can return null if it doesn't exist.
    }

    @Override
    public Position<E> right(Position<E> v) throws IllegalArgumentException {
        return checkPosition(v).right; // It can return null if it doesn't exist.
    }

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Node<E> node = checkPosition(p);
        Node<E> parent = node.parent;

        // Root dont have brother
        if (parent == null) {
            return null;
        }

        // It can return null if it doesn't exist.
        if (node == parent.left) {
            return parent.right;
        } else {
            return parent.left;
        }
    }

    @Override
    public boolean hasLeft(Position<E> v) throws IllegalArgumentException {
        return checkPosition(v).left != null;
    }

    @Override
    public boolean hasRight(Position<E> v) throws IllegalArgumentException {
        return checkPosition(v).right != null;
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
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null;
    }

    @Override
    public Position<E> root() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) throws IllegalArgumentException, IllegalStateException {
        Node<E> node = checkPosition(v);
        if (node == root) {
            throw new IllegalStateException("Root must not have parent");
        }
        return node.parent;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) throws IllegalArgumentException {
        List<Position<E>> snapshot = new ArrayList<>(2);

        if (hasLeft(v)) {
            snapshot.add(left(v));
        }
        if (hasRight(v)) {
            snapshot.add(right(v));
        }

        return snapshot;
    }

    @Override
    public boolean isInternal(Position<E> v) throws IllegalArgumentException {
        Node<E> node = checkPosition(v);
        return node.left != null || node.right != null;
    }

    @Override
    public boolean isExternal(Position<E> v) throws IllegalArgumentException {
        Node<E> node = checkPosition(v);
        return node.left == null && node.right == null;
    }

    @Override
    public boolean isRoot(Position<E> v) throws IllegalArgumentException {
        return checkPosition(v) == root;
    }

    @Override
    public E replace(Position<E> v, E e) throws IllegalArgumentException {
        Node<E> node = checkPosition(v);
        E replaced = node.element;
        node.element = e;
        return replaced;
    }

    private Node<E> checkPosition(Position<E> v) throws IllegalArgumentException {
        if (!(v instanceof Node<E> node)) { // Cast Position -> Node
            throw new IllegalArgumentException("Not a valid position.");
        }

        if (node.parent == node) {
            throw new IllegalArgumentException("Position has been deleted.");
        }

        return node;
    }

    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (parent == this) throw new IllegalStateException("Position invalid.");
            return element;
        }

    }
}