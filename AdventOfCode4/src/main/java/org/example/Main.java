package org.example;
import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws IOException {
        int safeCount = 0;
        String filePath = "/home/me/advent2.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // each line is a report
            String report;
            List<Integer> levelDistance;
            while ((report = br.readLine()) != null) {
                // Split the line by spaces
                // each line is an array with an element of each column, or level
                String[] levels = report.split("\\s+");
                List<Integer> intLevel = new ArrayList<Integer>();
                levelDistance = new ArrayList<>();
                try {
                    for (int i = 0; i < levels.length - 1; i++) {
                        levelDistance.add(parseInt(levels[i]) - parseInt(levels[i + 1]));
                    }

                    for (int i = 0; i < levels.length; i++) {
                        intLevel.add(i, parseInt(levels[i]));
                    }

                    if (isSafe((ArrayList<Integer>) levelDistance)) {
                        safeCount++;
                    } else {
                        for (int i = 0; i < intLevel.size(); i++) {
                            List<Integer> changedCopy = new ArrayList<>(intLevel);
                            changedCopy.remove(i);
                            levelDistance = new ArrayList<>();
                            for (int j = 0; j < changedCopy.size() - 1; j++) {
                                levelDistance.add(changedCopy.get(j) - changedCopy.get(j + 1));
                            }
                            if(isSafe((ArrayList<Integer>) levelDistance)) {
                                safeCount++;
                                break;
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + report);
                }
            }
            System.out.println(safeCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isSafe(ArrayList<Integer> levelDistance) {
            boolean isSafe = false;
            boolean alwaysDecreases;
            boolean alwaysIncreases;
            boolean isGradual = false;

            alwaysIncreases = levelDistance.get(0) < 0 ? true : false;
            alwaysDecreases = levelDistance.get(0) > 0 ? true : false;

            if (alwaysIncreases) {
                for (int distance : levelDistance) {
                    if (distance >= 0) {
                        alwaysIncreases = false;
                        break;
                    }
                }
            } else if (alwaysDecreases) {
                for (int distance : levelDistance) {
                    if (distance <= 0) {
                        alwaysDecreases = false;
                        break;
                    }
                }
            }

            if (Collections.max(levelDistance) < 4 && Collections.min(levelDistance) > -4)
            {
                isGradual = true;
            }
            if (isGradual && (alwaysDecreases || alwaysIncreases)) {
                isSafe = true;
            }
        return isSafe;
    }
}