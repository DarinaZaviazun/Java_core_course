package main.part6.part5;

public class Part5 {
    public static void main(String[] args) {
        Tree<Integer> nodes = new Tree<>();
        nodes.add(3);
        nodes.add(7);
        nodes.add(1);
        nodes.add(8);
        nodes.add(6);
        nodes.add(2);
        nodes.add(5);
//        nodes.add(2);
//        nodes.add(new Integer[]{5, 20, 9, 1});
//        System.out.println(nodes);
//        System.out.println(nodes.remove(6));
//        System.out.println(nodes);
        nodes.print();
    }
}

