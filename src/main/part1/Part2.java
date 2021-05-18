package main.part1;

public class Part2 {
    public static void main(String[] args) {
        int sum = 0;
        for (int a = 0; a < args.length; a++) {
            sum += Integer.parseInt(args[a]);
        }
        System.out.print(sum);
    }
}
