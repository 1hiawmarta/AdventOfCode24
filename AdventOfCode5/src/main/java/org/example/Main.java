package org.example;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "/home/me/advent3.txt";
        int multiplication = 0;
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        ArrayList<String> allMatches = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // each line is a report
            String line;
            while ((line = br.readLine()) != null) {
                searchMatches(line, regex, allMatches);
            }
            for (int i = 0; i < allMatches.size(); i++) {
                // mul(123,2)
                String[] digits = allMatches.get(i).substring(4, allMatches.get(i).length() - 1).split(",");
                multiplication += parseInt(digits[0]) * parseInt(digits[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(multiplication);
    }

    public static void searchMatches(String line, String regex, ArrayList<String> allMatches) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        while(matcher.find()) {
            allMatches.add(matcher.group());
        }

    }
}