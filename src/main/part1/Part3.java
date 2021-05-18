package main.part1;

public class Part3 {
    public static void main(String[] args) {
        if (args.length > 0) {
            for (int a = 0; a < args.length - 1; a++) {
                System.out.print(args[a] + " ");
            }
            System.out.print(args[args.length - 1]);
        }
    }
}
