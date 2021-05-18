package main.part6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>{
    private int[] row;
    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        this.row = new int[secBound - firstBound + 1];
        if (reversedOrder){
            int b = secBound;
            for (int a = 0; a < row.length; a++) {
                row[a] = b;
                b--;
            }
        }
        else {
            int b = firstBound;
            for (int a = 0; a < row.length; a++) {
                row[a] = b;
                b++;
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    private final class IteratorImpl implements Iterator<Integer> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < row.length;
        }

        @Override
        public Integer next() {
            if (row.length <= current) throw new NoSuchElementException();
            return row[current++];
        }
    }
}

