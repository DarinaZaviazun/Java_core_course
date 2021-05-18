package main.part3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {

    public static void main(String[] args) {
        System.out.println(decimal2Roman(82));
        System.out.println(roman2Decimal("II"));
    }

    public static String decimal2Roman(int dec) {
        int[] decimals = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder("");
        for (int a = 0; a < decimals.length; a++) {
            if (dec / decimals[a] > 0) {
                int b = dec / decimals[a];
                for (int c = 0; c < b; c++) {
                    sb.append(romans[a]);
                    dec -= decimals[a];
                }
            }
        }
        return sb.toString();
    }

    public static int roman2Decimal(String roman) {

        int summ = 0;
        int[] decimals = {1, 4, 5, 9, 10, 40, 50, 90, 100};
        String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        while (roman.length() > 0) {
            for (int a = 0; a < romans.length; a++) {
                Pattern pattern = Pattern.compile(romans[a] + "$");
                Matcher matcher = pattern.matcher(roman);
                if (matcher.find()) {
                    summ += decimals[a];
                    roman = roman.replaceFirst(romans[a] + "$", "");
                }
            }
        }
        return summ;
    }
}
