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
        String regex2 = "don't\\(\\).*?do\\(\\)";
        String regex3 = "don't\\(\\)(?!.*do\\(\\)).*$";
        ArrayList<String> allMatches = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // each line is a report
            String line;
            String wholeText = "";
            while ((line = br.readLine()) != null) {
                wholeText += line;
            }

            wholeText = removeDisabled(wholeText, regex2);
            wholeText = removeLastDisabled(wholeText, regex3);
            searchMatches(wholeText, regex, allMatches);

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

    public static String removeDisabled(String line, String regex2) {
        Pattern pattern = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(line);
        StringBuffer cleanedLine = new StringBuffer();

        while (matcher.find()) {
            // copia lo de antes de la coincidencia
            // la coincidencia es ""
            matcher.appendReplacement(cleanedLine, ""); // Reemplaza la coincidencia con ""
        }
        // copia el final
        matcher.appendTail(cleanedLine);
        return cleanedLine.toString();
    }

    public static String removeLastDisabled(String line, String regex3) {
        Pattern pattern = Pattern.compile(regex3);
        Matcher matcher = pattern.matcher(line);

        int lastStart = -1;

        // Encuentra la última coincidencia
        while (matcher.find()) {
            lastStart = matcher.start();
        }

        // Si hay una coincidencia, elimina la última
        if (lastStart != -1) {
            return line.substring(0, lastStart);
        }

        // Si no hay coincidencias, devuelve la línea original
        return line;
    }
} 