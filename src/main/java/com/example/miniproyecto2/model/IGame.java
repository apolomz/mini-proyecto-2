package com.example.miniproyecto2.model;

/**
 * Interface for the Sudoku game model.
 */
public interface IGame {

    /**
     * Checks if a move is valid.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param number The number to place.
     * @return True if the move is valid, false otherwise.
     */
    boolean isValidMove(int row, int col, int number);

    /**
     * Makes a move on the grid.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param number The number to place.
     */
    void makeMove(int row, int col, int number);

    /**
     * Checks if the puzzle is complete.
     *
     * @return True if complete, false otherwise.
     */
    boolean isComplete();

    /**
     * Gets the solution array.
     *
     * @return The solution grid.
     */
    int[][] getSolution();
}
