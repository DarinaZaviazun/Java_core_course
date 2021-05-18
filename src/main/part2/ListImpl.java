package main.part2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private Node head;
    private Node tail;
    private Node found;
    private Node found2;
    private int size = 0;
    StringBuilder stroka = new StringBuilder("");

    static class Node {
        private Object object;
        private Node linkToNext;

        public Node(Object object, Node linkToNext) {
            this.object = object;
            this.linkToNext = linkToNext;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "object=" + object +
                    ", linkToNext=" + (linkToNext == null ? "null" : linkToNext.object) +
                    '}';
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node currentNode = head;
        private Node nextNode = head;


        @Override
        public boolean hasNext() {
            return currentNode != null && currentNode.linkToNext != null;
        }

        @Override
        public Object next() {
            if (currentNode.linkToNext != null) {
                currentNode = nextNode;
                nextNode = currentNode.linkToNext;
                return currentNode.object;
            } else throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            ListImpl.this.remove(currentNode.object);
        }
    }

    @Override
    public void addFirst(Object element) {
        if (head == null) {
            head = new Node(element, null);
            tail = head;
        } else {
            this.head = new Node(element, head);
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(element, null);
        if (tail == null) {
            head = newNode;
            tail = head;
        } else {
            tail.linkToNext = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        if (head != null) {
            if (head.linkToNext != null) {
                head = head.linkToNext;
            } else {
                head = null;
                tail = null;
            }
            size--;
        }
    }

    @Override
    public void removeLast() {
        if (size == 1) {
            head = null;
            tail = null;
        } else if (tail != null) {
            found = null;
            recursion(head, tail);
            found.linkToNext = null;
            tail = found;
        } else return;
        size--;
    }

    private void recursion(Node node, Node node2) {
        if (!node.linkToNext.equals(node2))
            recursion(node.linkToNext, node2);
        else found = node;
    }

    @Override
    public Object getFirst() {
        return head == null? null : head.object;
    }

    @Override
    public Object getLast() {
        return tail == null ? null : tail.object;
    }

    @Override
    public Object search(Object element) {
        found = null;
        recursion2(head, element);
        return found == null ? null : found.object;
    }

    private void recursion2(Node node, Object node2) {
        if (node != null && node.object != node2) {
            recursion2(node.linkToNext, node2);
        } else found = node;
    }

    @Override
    public boolean remove(Object element) {
        found = null;
        found2 = null;
        if (size > 0) {
            if (element == (head.object)) {
                removeFirst();
                return true;
            }
            if (element == (tail.object)) {
                removeLast();
                return true;
            }
            recursion3(head, element);
            recursion2(head, element);
            if (found2 != null && found != null) {
                found.linkToNext = found2.linkToNext;
                size--;
                return true;
            }
        }
        return false;
    }

    private void recursion3(Node node, Object obj) {
        if (node != null && node.linkToNext != null && node.linkToNext.object != obj)
            recursion3(node.linkToNext, obj);
        else found2 = node;
    }

    @Override
    public String toString() {
        stroka = new StringBuilder("");
        StringBuilder sb = new StringBuilder("[");
        if (head != null) {
            sb.append(head.object);
            if (head.linkToNext != null) {
                sb.append(", ").append(publication(head.linkToNext));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String publication(Node node) {
        stroka.append(node.object);
        if (node.linkToNext != null) {
            stroka.append(", ");
            publication(node.linkToNext);
        }
        return stroka.toString();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst(null);
        list.addFirst("C");
        System.out.println(list);
        System.out.println(list.search("B"));
        list.remove("C");
        System.out.println(list);
    }
}

