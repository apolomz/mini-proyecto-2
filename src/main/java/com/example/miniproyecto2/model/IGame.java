package com.example.miniproyecto2.model;

public interface IGame {
    void intializeBoard();
    boolean isValid(int row, int col, int val);
    boolean isComplete();
}
