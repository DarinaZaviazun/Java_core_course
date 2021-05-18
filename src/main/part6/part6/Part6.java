package main.part6.part6;

import java.io.*;

public class Part6 {

    public static void main(String[] args) {
        String file = "";
        String task = "";
        for (int a = 0; a < args.length; a++){
            if (args[a].equals("-i") || args[a].equals("--input"))
                file = args[a + 1];
            if (args[a].equals("-t") || args[a].equals("--task"))
                task = args[a + 1];
        }
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready())
                sb.append(reader.readLine()).append(" ");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        switch (task) {
            case "frequency":
                Part61.frequencyTask(sb.toString());
                break;
            case "length":
                Part62.lengthTask(sb.toString());
                break;
            case "duplicates":
                Part63.duplicatesTask(sb.toString());
                break;
            default:
                break;
        }
    }
}
