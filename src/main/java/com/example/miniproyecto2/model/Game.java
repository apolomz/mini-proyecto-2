package com.example.miniproyecto2.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game class that implements the Sudoku logic.
 */
public class Game implements IGame {
    private int[][] grid;  // Grid visible to the player
    private int[][] solution;  // The full solution of the puzzle

    public Game() {
        grid = new int[6][6];  // Initialize the grid for the user
        solution = new int[6][6];  // Initialize the full solution grid
        generateSolution();
    }

    // Generates a full valid Sudoku solution
    private void generateSolution() {
        System.out.println("Solución del juego generado");
        fillGrid(solution);  // Fill the solution grid with a valid Sudoku
    }

    // Fills the grid using backtracking
    private boolean fillGrid(int[][] grid) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(i);
        }

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (grid[row][col] == 0) {
                    Collections.shuffle(numbers);  // Shuffle numbers to ensure randomness
                    for (int number : numbers) {
                        if (isValidMoveForSolution(grid, row, col, number)) {
                            grid[row][col] = number;
                            if (fillGrid(grid)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;  // If no number is valid, backtrack
                }
            }
        }
        return true;
    }

    // Validates if a number can be placed in a specific cell for the solution
    private boolean isValidMoveForSolution(int[][] grid, int row, int col, int number) {
        // Check row
        for (int i = 0; i < 6; i++) {
            if (grid[row][i] == number) return false;
        }

        // Check column
        for (int i = 0; i < 6; i++) {
            if (grid[i][col] == number) return false;
        }

        // Check block (2x3)
        int blockRowStart = (row / 2) * 2;
        int blockColStart = (col / 3) * 3;
        for (int r = blockRowStart; r < blockRowStart + 2; r++) {
            for (int c = blockColStart; c < blockColStart + 3; c++) {
                if (grid[r][c] == number) return false;
            }
        }

        return true;
    }

    @Override
    public boolean isValidMove(int row, int col, int number) {
        // Validate if the move is correct according to the solution
        return solution[row][col] == number;
    }

    @Override
    public void makeMove(int row, int col, int number) {
        // If the move is valid, update the player's grid
        if (isValidMove(row, col, number)) {
            grid[row][col] = number;
        }
    }

    @Override
    public boolean isComplete() {
        // Check if the player's grid matches the solution
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (grid[row][col] != solution[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Get position and return the array
    public int[][] getSolution() {
        return solution;  // Return the solution grid
    }
}
