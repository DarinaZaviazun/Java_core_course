package main.part6.part5;

public class Tree<E extends Comparable<E>> {
    private Node<E> root;
    private int capacity;

    public boolean add(E element) {
        int init = capacity;
        root = findPlace(root, element);
        return capacity != init;
    }

    private Node<E> findPlace(Node<E> current, E element) {
        if (current == null) {
            capacity++;
            return new Node<>(element, null, null);
        }
        int i = element.compareTo(current.object);
        if (i > 0) {
            current.left = findPlace(current.left, element);
        }
        if (i < 0) {
            current.right = findPlace(current.right, element);
        }
        return current;
    }

    public void add(E[] elements) {
        for (E elem : elements) add(elem);
    }

    public boolean remove(E element) {
        int init = capacity;
        root = deleteRecursion(root, element);
        return capacity != init;
    }

    private Node<E> deleteRecursion(Node<E> current, E value) {
        if (current == null) return null;
        int a = value.compareTo(current.object);
        if (a == 0) {
            capacity--;
            if (current.left == null && current.right == null) return null;
            if (current.right == null) return current.left;
            if (current.left == null) return current.right;
            E smallestValue = findSmallestValue(current.left);
            current.object = smallestValue;
            current.left = deleteRecursion(current.left, smallestValue);
            return current;
        }
        if (a < 0) current.right = deleteRecursion(current.right, value);
        if (a > 0) current.left = deleteRecursion(current.left, value);
        return current;
    }

    private E findSmallestValue(Node<E> init) {
        return init.right == null ? init.object : findSmallestValue(root.right);
    }

    public void print() {
        traverseInOrder(root, 0);
    }

    public void traverseInOrder(Node node, int repeat) {
        int count = repeat + 2;
        if (node != null) {
            traverseInOrder(node.right, count);
            count -= 2;
            for (int a = 0; a < count; a++)
                System.out.print(" ");
            System.out.println(node.object);
            count += 2;
            traverseInOrder(node.left, count);
        }
    }

    private static final class Node<E> {
        private E object;
        private Node<E> left;
        private Node<E> right;

        public Node(E object, Node<E> left, Node<E> right) {
            this.object = object;
            this.left = left;
            this.right = right;
        }
    }
}
