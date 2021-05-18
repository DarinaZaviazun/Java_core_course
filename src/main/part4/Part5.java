package main.part4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Part5 {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String input = reader.readLine();
                if (input.equals("stop")) break;
                String[] arr = input.split(" ");
                String word = arr[0];
                String local = arr[1];
                Locale locale = new Locale(local);
                ResourceBundle rb = ResourceBundle.getBundle("resources", locale);
                String string = rb.getString(word);
                System.out.println(string);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

