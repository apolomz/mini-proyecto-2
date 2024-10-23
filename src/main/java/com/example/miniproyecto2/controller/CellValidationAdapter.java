package com.example.miniproyecto2.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class CellValidationAdapter implements ChangeListener<String> {
    private final GameController controller;
    private final int row;
    private final int col;

    public CellValidationAdapter(GameController controller, int row, int col) {
        this.controller = controller;
        this.row = row;
        this.col = col;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("[1-6]?")) {
            Platform.runLater(() -> controller.getCells()[row][col].setText(""));
        } else if (!newValue.isEmpty()) {
            int number = Integer.parseInt(newValue);
            if (controller.getGame().isValidMove(row, col, number)) {
                controller.getGame().makeMove(row, col, number);
                Platform.runLater(() -> {
                    controller.getCells()[row][col].setStyle("-fx-text-fill: #325AAF; -fx-border-color: #435D6C; -fx-border-radius: 0;");
                    controller.getCells()[row][col].setEditable(false);
                });
                controller.checkWinCondition();
            } else {
                Platform.runLater(() -> controller.getCells()[row][col].setStyle("-fx-text-fill: #EF4B4C; -fx-border-color: #435D6C; -fx-border-radius: 0;"));
            }
        }
    }
}