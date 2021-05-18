package main.part6.part2;

import java.util.*;

public class Part2 {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        LinkedList<Integer> link = new LinkedList<>();
        filling(arr, link);
        System.out.println("ArrayList#Index: " + Part2.removeByIndex(arr, 4) + " ms");
        System.out.println("LinkedList#Index: " + Part2.removeByIndex(link, 4) + " ms");
        filling(arr, link);
        System.out.println("ArrayList#Iterator: " + Part2.removeByIterator(arr, 4) + " ms");
        System.out.println("LinkedList#Iterator: " + Part2.removeByIterator(link, 4) + " ms");
    }

    public static void filling(List<Integer> list, List<Integer> list2) {
        for (int a = 0; a < 10000; a++) {
            list.add(a);
            list2.add(a);
        }
    }

    public static long removeByIndex(final List<Integer> list, final int k) {
        long l = System.currentTimeMillis();
        List<Integer> copy = list instanceof ArrayList ? new ArrayList<>(list) : new LinkedList<>(list);
        int j = 0;
        for (int a = k - 1; a < copy.size(); a += k - 1 + j) {
            if (list.size() == 1) break;
            list.remove(a);
            j = 0;
            if (a + k - 1 >= list.size()) j = - list.size();
            if (k > list.size()) j = - list.size() - 1;
            System.out.println(list);
        }
        return System.currentTimeMillis() - l;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long l = System.currentTimeMillis();
        int count = 0;
        int dop = k;
        while (list.size() != 1) {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (++count == dop) {
                    iterator.remove();
                    dop += k;
                }
            }
        }
        return System.currentTimeMillis() - l;
    }
}

