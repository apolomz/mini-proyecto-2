package com.example.miniproyecto2.model;

/**
 * Interface for the Sudoku game model.
 */
public interface IGame {

    // Check if a move is valid
    boolean isValidMove(int row, int col, int number);

    // Make a move on the grid
    void makeMove(int row, int col, int number);

    // Check if the puzzle is complete
    boolean isComplete();

    // Get the current state of the grid
    int[][] getCurrentGrid();

    int[][] getSolution();
}
