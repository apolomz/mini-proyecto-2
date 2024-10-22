package com.example.miniproyecto2.model;

public class Game implements IGame{
    private int[][] board;

    public Game() {
        board = new int[6][6];
        intializeBoard();
    }

    public void intializeBoard() {

    }

    @Override
    public boolean isValid(int row, int col, int val) {
        return false;
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
