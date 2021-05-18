package main.part2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private int size;
    private Object first;
    private Object[] stack;

    public StackImpl() {
        size = 0;
        stack = new Object[0];
        first = null;
    }

    @Override
    public void clear() {
        size = 0;
        stack = new Object[0];
        first = null;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentId = size;

        @Override
        public boolean hasNext() {
            return currentId <= stack.length && currentId > 0;
        }

        @Override
        public Object next() {
            if (--currentId < 0) throw new NoSuchElementException();
            return stack[currentId];
        }

        @Override
        public void remove() {
            Object[] newO = new Object[stack.length - 1];
            int j = 0;
            for (int a = 0; a < stack.length; a++) {
                if (a != currentId) {
                    newO[j] = stack[a];
                    j++;
                }
            }
            stack = newO;
            size--;
        }
    }

    @Override
    public void push(Object element) {
        Object[] newO = new Object[size + 1];
        System.arraycopy(stack, 0, newO, 0, size);
        newO[size] = element;
        first = newO[size];
        stack = newO;
        size++;
    }

    @Override
    public Object pop() {
        if (size > 1) {
            Object t = first;
            first = stack[size - 2];
            Object[] newO = new Object[size - 1];
            System.arraycopy(stack, 0, newO, 0, size - 1);
            stack = newO;
            size--;
            return t;
        }
        if (size == 1) {
            Object t = first;
            stack = new Object[0];
            first = null;
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
        for (int a = 0; a < stack.length; a++) {
            str.append(stack[a]);
            if (a < size - 1) str.append(", ");
        }
        str.append("]");
        return str.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push(null);
        stack.push("C");
        System.out.println(stack);
        Iterator<Object> iterator = stack.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            System.out.println(o);
            if (o == "C") iterator.remove();
        }
        System.out.println(stack);
    }
}

