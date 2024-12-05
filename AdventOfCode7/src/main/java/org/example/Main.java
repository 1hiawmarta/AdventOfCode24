import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList to hold the grid
        ArrayList<String> grid = new ArrayList<>();
        String target = "XMAS";

        // Read the grid from a file
        try (BufferedReader br = new BufferedReader(new FileReader("/home/me/advent4.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                grid.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        int wordLength = target.length();

        // Directions: (dx, dy) pairs
        int[][] directions = {
                {0, 1},   // Right
                {0, -1},  // Left
                {1, 0},   // Down
                {-1, 0},  // Up
                {1, 1},   // Down-right diagonal
                {1, -1},  // Down-left diagonal
                {-1, 1},  // Up-right diagonal
                {-1, -1}  // Up-left diagonal
        };

        int count = 0;

        // Loop through each cell in the grid
        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.get(x).length(); y++) {
                // Check all directions
                for (int[] dir : directions) {
                    if (checkWord(grid, target, x, y, dir[0], dir[1])) {
                        count++;
                    }
                }
            }
        }

        System.out.println("Total occurrences of XMAS: " + count);
    }

    // Method to check if the word exists starting at (x, y) in a given direction
    public static boolean checkWord(ArrayList<String> grid, String target, int x, int y, int dx, int dy) {
        int wordLength = target.length();
        for (int i = 0; i < wordLength; i++) {
            int nx = x + i * dx;
            int ny = y + i * dy;
            // Check bounds
            if (nx < 0 || nx >= grid.size() || ny < 0 || ny >= grid.get(nx).length()) {
                return false;
            }
            // Check character match
            if (grid.get(nx).charAt(ny) != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
