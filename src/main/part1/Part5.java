package main.part1;

public class Part5 {
    public static void main(String[] args) {
        try {
            String[] number = args[0].split("");
            int sum = 0;
            for (int a = 0; a < number.length; a++) {
                sum += Integer.parseInt(number[a]);
            }
            System.out.print(sum);
        }catch (Exception e){}
    }
}
