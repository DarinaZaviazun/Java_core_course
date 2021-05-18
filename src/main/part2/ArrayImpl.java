//package main.part2;
//
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//public class ArrayImpl implements Array {
//    private Object[] array;
//    private int size = 0;
//
//    public ArrayImpl(int capacity) {
//        this.array = new Object[capacity];
//    }
////
////    @Override
////    public void clear() {
////        this.array = new Object[0];
////        size = 0;
////    }
////
////    @Override
////    public int size() {
////        return size;
////    }
////
////    @Override
////    public Iterator<Object> iterator() {
////        return new IteratorImpl();
////    }
//
//    private class IteratorImpl implements Iterator<Object> {
//        private int currentInd = 0;
//
//        @Override
//        public boolean hasNext() {
//            return currentInd < array.length;
//        }
//
//        @Override
//        public Object next() {
//            if (array.length <= currentInd) throw new NoSuchElementException();
//            return array[currentInd++];
//        }
////
////        @Override
////        public void remove() {
////            ArrayImpl.this.remove(--currentInd);
////        }
////    }
//
//    @Override
//    public void add(Object element) {
//        if (size == array.length) {
//            Object[] newO = new Object[array.length + 1];
//            System.arraycopy(array, 0, newO, 0, array.length);
//            newO[newO.length - 1] = element;
//            array = newO;
//        } else {
//            array[size] = element;
//        }
//        size++;
//    }
//
//    @Override
//    public void set(int index, Object element) {
//        array[index] = element;
//    }
//
//    @Override
//    public Object get(int index) {
//        return array[index];
//    }
//
//    @Override
//    public int indexOf(Object element) {
//        for (int a = 0; a < size; a++) {
//            if (array[a] != null && array[a].equals(element)) return a;
//        }
//        return -1;
//    }
//
//    @Override
//    public void remove(int index) {
//        Object[] newO = new Object[array.length - 1];
//        int j = 0;
//        for (int a = 0; a < array.length; a++) {
//            if (a != index) {
//                newO[j] = array[a];
//                j++;
//            }
//        }
//        array = newO;
//        size--;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder strB = new StringBuilder("[");
//        for (int a = 0; a < array.length; a++) {
//            strB.append(array[a]);
//            if (a < array.length - 1) strB.append(", ");
//        }
//        strB.append(']');
//        return strB.toString();
//    }
//
//    public static void main(String[] args) {
//        ArrayImpl array = new ArrayImpl(5);
//        array.add("A");
//        array.add("B");
//        array.add(null);
//        array.add("C");
//        System.out.println(array.indexOf(null));
//    }
//}
