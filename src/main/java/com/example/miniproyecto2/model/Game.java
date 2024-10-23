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

    /**
     * Initializes the game grid and generates a solution.
     */
    public Game() {
        grid = new int[6][6];  // Initialize the grid for the user
        solution = new int[6][6];  // Initialize the full solution grid
        generateSolution();
    }

    /**
     * Generates a full valid Sudoku solution.
     */
    private void generateSolution() {
        System.out.println("Solución del juego generado");
        fillGrid(solution);  // Fill the solution grid with a valid Sudoku
    }

    /**
     * Fills the grid using backtracking to create a valid Sudoku.
     *
     * @param grid The grid to fill.
     * @return True if the grid is successfully filled, false otherwise.
     */
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

    /**
     * Validates if a number can be placed in a specific cell for the solution.
     *
     * @param grid The grid to check.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param number The number to place.
     * @return True if the move is valid, false otherwise.
     */
    private boolean isValidMoveForSolution(int[][] grid, int row, int col, int number) {
        for (int i = 0; i < 6; i++) {
            if (grid[row][i] == number) return false;
        }
        for (int i = 0; i < 6; i++) {
            if (grid[i][col] == number) return false;
        }

        int blockRowStart = (row / 2) * 2;
        int blockColStart = (col / 3) * 3;
        for (int r = blockRowStart; r < blockRowStart + 2; r++) {
            for (int c = blockColStart; c < blockColStart + 3; c++) {
                if (grid[r][c] == number) return false;
            }
        }

        return true;
    }

    /**
     * Checks if a move is valid.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param number The number to place.
     * @return True if the move is valid, false otherwise.
     */
    @Override
    public boolean isValidMove(int row, int col, int number) {
        // Validate if the move is correct according to the solution
        return solution[row][col] == number;
    }

    /**
     * Makes a move on the grid.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param number The number to place.
     */
    @Override
    public void makeMove(int row, int col, int number) {
        if (isValidMove(row, col, number)) {
            grid[row][col] = number;
        }
    }

    /**
     * Checks if the puzzle is complete.
     *
     * @return True if complete, false otherwise.
     */
    @Override
    public boolean isComplete() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (grid[row][col] != solution[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the solution grid.
     *
     * @return The solution grid.
     */
    public int[][] getSolution() {
        return solution;  // Return the solution grid
    }
}
