package arvore.busca;

import arvore.Position;

import arvore.binaria.LinkedBinaryTree;

public class BinarySearchTree<E extends Comparable<E>> {
    private final LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public Iterable<Position<E>> inOrder() {
        return tree.inOrderIterable();
    }

    /**
     * Auxiliary method that searches for an element in the tree.
     *
     * @return The position where the element is located,
     * or the last leaf position where it SHOULD be if it doesn't exist.
     */
    private Position<E> treeSearch(Position<E> p, E key) {
        if (tree.isExternal(p)) {
            return p; // Reached the bottom of the tree
        }

        int comp = key.compareTo(p.getElement());

        if (comp == 0) {
            return p; // Found it
        } else if (comp < 0 && tree.hasLeft(p)) {
            return treeSearch(tree.left(p), key);
        } else if (comp > 0 && tree.hasRight(p)) {
            return treeSearch(tree.right(p), key);
        }

        // Returns where the search stopped (will be the parent of the new node on insertion)
        return p;
    }

    /**
     * Inserts a new element into the Binary Search Tree
     */
    public void insert(E key) {
        if (tree.isEmpty()) {
            tree.addRoot(key);
            return;
        }

        Position<E> p = treeSearch(tree.root(), key);
        int comp = key.compareTo(p.getElement());

        if (comp == 0) {
            return;
        } else if (comp < 0) {
            tree.addLeft(p, key);
        } else {
            tree.addRight(p, key);
        }
    }

    /**
     * Removes an element from the tree and restructures it if necessary.
     *
     * @return The removed element, or null if it was not found.
     */
    public E remove(E element) {
        if (tree.isEmpty()) {
            return null;
        }

        Position<E> p = treeSearch(tree.root(), element);

        // Element does not exist in the tree
        if (element.compareTo(p.getElement()) != 0) {
            return null;
        }

        E removedElement = p.getElement();

        // The node has 2 children -> Successor
        if (tree.hasLeft(p) && tree.hasRight(p)) {
            // Find the successor
            Position<E> successor = tree.right(p);
            while (tree.hasLeft(successor)) {
                successor = tree.left(successor);
            }

            // Replace the target node's element with the successor's element
            tree.replace(p, successor.getElement());

            // Move the pointer 'p' to point to the physical successor (this node will be removed)
            p = successor;
        }

        tree.remove(p);

        return removedElement;
    }
}