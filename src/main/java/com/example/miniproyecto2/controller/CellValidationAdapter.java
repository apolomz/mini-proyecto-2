package com.example.miniproyecto2.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Adapter class to validate input in the Sudoku cells.
 */
public class CellValidationAdapter implements ChangeListener<String> {
    /**
     * @param controller The GameController instance handling the game logic and UI.
     * @param row The row index of the cell being validated.
     * @param col The column index of the cell being validated.
     */
    private final GameController controller;
    private final int row;
    private final int col;

    /**
     * Constructs a CellValidationAdapter for a specific cell.
     *
     * @param controller The GameController instance.
     * @param row The row of the cell.
     * @param col The column of the cell.
     */
    public CellValidationAdapter(GameController controller, int row, int col) {
        this.controller = controller;
        this.row = row;
        this.col = col;
    }

    /**
     * Validates the input in a cell when its text changes.
     *
     * @param observable The observable value of the text property.
     * @param oldValue The old text value before the change.
     * @param newValue The new text value after the change.
     */
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
