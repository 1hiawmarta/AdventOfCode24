package org.example;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int safeCount = 0;
        String filePath = "/home/me/advent2.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // each line is a report
            String report;
            List<Integer> levelDistance;
            boolean alwaysDecreases;
            boolean alwaysIncreases;
            while ((report = br.readLine()) != null) {
                // Split the line by spaces
                // each line is an array with an element of each column, or level
                String[] levels = report.split("\\s+");
                levelDistance  = new ArrayList<>();
                boolean isGradual = false;
                try {
                    for (int i = 0; i < levels.length - 1; i++) {
                        levelDistance.add(Integer.parseInt(levels[i]) - Integer.parseInt(levels[i + 1]));
                    }

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
                        safeCount++;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + report);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(safeCount);
    }
}