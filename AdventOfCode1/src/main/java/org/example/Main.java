package org.example;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> col1 = new ArrayList<>();
        List<Integer> col2 = new ArrayList<>();
        int distance = 0;
        String filePath = "/home/me/advent1.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by spaces
                // each line is an array with an element of each column
                String[] columns = line.split("\\s+");
                try {
                    // Parse and add to HashSet
                    col1.add(Integer.parseInt(columns[0]));
                    col2.add(Integer.parseInt(columns[1]));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Sort in ascending order
        Collections.sort(col1);
        Collections.sort(col2);

        for (int i = 0; i < col1.size(); i++) {
            distance += Math.abs(col1.get(i) - col2.get(i));
        }

        System.out.println(distance);
    }
}