package main.part1;

public class Part7 {
    public static void main(String[] args) {
        String ar = " ==> ";
        System.out.println("A" + ar + str2int("A") + ar + int2str(str2int("A")));
        System.out.println("B" + ar + str2int("B") + ar + int2str(str2int("B")));
        System.out.println("Z" + ar + str2int("Z") + ar + int2str(str2int("Z")));
        System.out.println("AA" + ar + str2int("AA") + ar + int2str(str2int("AA")));
        System.out.println("AZ" + ar + str2int("AZ") + ar + int2str(str2int("AZ")));
        System.out.println("BA" + ar + str2int("BA") + ar + int2str(str2int("BA")));
        System.out.println("ZZ" + ar + str2int("ZZ") + ar + int2str(str2int("ZZ")));
        System.out.println("AAA" + ar + str2int("AAA") + ar + int2str(str2int("AAA")));
    }

    public static int str2int(String number){
        int rezult = 0;
        if (number.length() == 1){
            rezult = number.charAt(0) - 64;

        }
        if (number.length() == 2){
            int a = number.charAt(0) - 64;
            int b = number.charAt(1) - 64;
            rezult = 26*a + b;
        }
        if (number.length() == 3){
            int a = number.charAt(0) - 64;
            int b = number.charAt(1) - 64;
            int c = number.charAt(2) - 64;
            rezult = 26*26*a + 26*b + c;
        }
        return rezult;
    }

    public static String int2str(int number){
        String rezult = "";

        if (number < 27){
            char rez = (char)(number + 64);
            rezult = Character.toString(rez);
        }
        if (number > 26 && number < 703){
            char rez = (char) ((number-1)/26 + 64);
            char rez2 = (char) ((number-1)%26 + 65);
            rezult = Character.toString(rez);
            rezult += Character.toString(rez2);
        }
        if (number > 702){
            char rez = (char)((number % 2 == 0 ? number/(26*27) : number/(26*26)) + 64);
            char rez2 =(char) ((number-105)/26 - 26*(rez-64) + 68);
            char rez3 =(char) ((number-1)%26 + 65);
            rezult = Character.toString(rez);
            rezult += Character.toString(rez2);
            rezult += Character.toString(rez3);
        }
        return rezult;
    }

    public static String rightColumn (String number){
        return int2str(str2int(number) + 1);
    }
}
