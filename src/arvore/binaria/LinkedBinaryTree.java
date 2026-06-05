package arvore.binaria;

import arvore.Position;

import java.util.Iterator;

public class LinkedBinaryTree<E> implements BinaryTree<E> {
    private Node<E> root = null;
    private int size = 0;

    @Override
    public Position<E> left(Position<E> v) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public Position<E> right(Position<E> v) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public boolean hasLeft(Position<E> v) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean hasRight(Position<E> v) throws IllegalArgumentException {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
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
        return null;
    }

    @Override
    public Position<E> parent(Position<E> v) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> v) throws IllegalArgumentException {
        return null;
    }

    @Override
    public boolean isInternal(Position<E> v) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isExternal(Position<E> v) throws IllegalArgumentException {
        return false;
    }

    @Override
    public boolean isRoot(Position<E> v) throws IllegalArgumentException {
        return false;
    }

    @Override
    public E replace(Position<E> v, E e) throws IllegalArgumentException {
        return null;
    }

    private Node<E> checkPosition(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E> node)) { // Cast Position -> Node
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