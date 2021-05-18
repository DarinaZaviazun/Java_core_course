package main.part1;

public class Part6 {
    public static void main(String[] args) {
        StringBuilder rez = new StringBuilder();

        if (Integer.parseInt(args[0]) != 0) {
            int[] rezult = new int[Integer.parseInt(args[0])];
            int s = 0;
            boolean flag = true;

            while (flag) {
                for (int f = 2; f < 100; f++) {
                    int count = 0;
                    for (int g = 1; g < f; g++)
                        if (f % g == 0) count++;
                    if (count < 2 && s < rezult.length) {
                        rezult[s] = f;
                        s++;
                        if (s == rezult.length) flag = false;
                    }
                }
            }
            for (int i : rezult) rez.append(i).append(" ");
            System.out.print(rez.toString().trim());
        }
    }
}
