package main.part1;

public class Part4 {
    public static void main(String[] args) {
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            if (a < b) {
                int c = a;
                a = b;
                b = c;
            }
            int c;
            while (b != 0) {
                if (a % b != 0) {
                    c = a % b;
                    a = b;
                    b = c;
                } else {
                    System.out.print(b);
                    break;
                }
            }
        }catch (Exception e){}
    }
}
