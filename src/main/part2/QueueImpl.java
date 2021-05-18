package main.part2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private int size;
    private Object first;
    private Object last;
    private Object[] queue;
 
    public QueueImpl() {
        size = 0;
        queue = new Object[0];
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
        queue = new Object[0];
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentId = 0;

        @Override
        public boolean hasNext() {
            return currentId < queue.length;
        }

        @Override
        public Object next() {
            if (queue.length <= currentId) throw new NoSuchElementException();
            return queue[currentId++];
        }

        @Override
        public void remove() {
            Object[] newO = new Object[queue.length - 1];
            int j = 0;
            for (int a = 0; a < queue.length; a++) {
                if (a != currentId - 1) {
                    newO[j] = queue[a];
                    j++;
                }
            }
            queue = newO;
            size--;
            currentId--;
        }
    }

    @Override
    public void enqueue(Object element) {
        if (first == null) {
            first = element;
            last = first;
            queue = new Object[1];
            queue[0] = last;
        } else {
            Object[] newO = new Object[size + 1];
            System.arraycopy(queue, 0, newO, 0, size);
            newO[size] = element;
            last = newO[size];
            first = newO[0];
            queue = newO;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if (size > 1) {
            Object t = first;
            first = queue[1];
            Object[] newO = new Object[size - 1];
            System.arraycopy(queue, 1, newO, 0, size - 1);
            queue = newO;
            first = queue[0];
            last = queue[size - 2];
            size--;
            return t;
        }
        if (size == 1) {
            Object t = first;
            queue = new Object[0];
            first = null;
            last = null;
            size = 0;
            return t;
        }
        return null;
    }

    @Override
    public Object top() {
        return first;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int a = 0; a < queue.length; a++) {
            str.append(queue[a]);
            if (a < size - 1) str.append(", ");
        }
        str.append("]");
        return str.toString();
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue(null);
        queue.enqueue("E");
        System.out.println(queue);
        Iterator<Object> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            System.out.println(o);
            if (o == "C") iterator.remove();
        }
        System.out.println(queue);
    }
}
