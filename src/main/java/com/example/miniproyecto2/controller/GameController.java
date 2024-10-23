package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.model.Game;
import com.example.miniproyecto2.model.IGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    private List<List<Integer>> rows;
    private List<List<Integer>> cols;
    private List<List<Integer>> block;

    private IGame game;

    private int help_counter = 0;

    private String[] head_position = {"A", "B", "C", "D", "E", "F"};

    @FXML
    private Button buttonHelp;

    @FXML
    private TextField cell_00, cell_01, cell_02, cell_03, cell_04, cell_05;
    @FXML
    private TextField cell_10, cell_11, cell_12, cell_13, cell_14, cell_15;
    @FXML
    private TextField cell_20, cell_21, cell_22, cell_23, cell_24, cell_25;
    @FXML
    private TextField cell_30, cell_31, cell_32, cell_33, cell_34, cell_35;
    @FXML
    private TextField cell_40, cell_41, cell_42, cell_43, cell_44, cell_45;
    @FXML
    private TextField cell_50, cell_51, cell_52, cell_53, cell_54, cell_55;

    @FXML
    private TextField[][] cells = new TextField[6][6];

    @FXML
    private Label helpText;

    @FXML
    private Button resetButton;

    @FXML
    void handleReset(ActionEvent event) {
        resetGame();
        helpText.setText("");
    }

    @FXML
    private void initialize() {
        rows = new ArrayList<>();
        cols = new ArrayList<>();
        block = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            rows.add(new ArrayList<>());
            cols.add(new ArrayList<>());
        }

        for (int i = 0; i < 6; i++) {
            block.add(new ArrayList<>());
        }

        initializeTextFieldGrid();
        setupValidation();
    }

    @FXML
    private void initializeTextFieldGrid() {
        cells[0][0] = cell_00; cells[0][1] = cell_01; cells[0][2] = cell_02; cells[0][3] = cell_03; cells[0][4] = cell_04; cells[0][5] = cell_05;
        cells[1][0] = cell_10; cells[1][1] = cell_11; cells[1][2] = cell_12; cells[1][3] = cell_13; cells[1][4] = cell_14; cells[1][5] = cell_15;
        cells[2][0] = cell_20; cells[2][1] = cell_21; cells[2][2] = cell_22; cells[2][3] = cell_23; cells[2][4] = cell_24; cells[2][5] = cell_25;
        cells[3][0] = cell_30; cells[3][1] = cell_31; cells[3][2] = cell_32; cells[3][3] = cell_33; cells[3][4] = cell_34; cells[3][5] = cell_35;
        cells[4][0] = cell_40; cells[4][1] = cell_41; cells[4][2] = cell_42; cells[4][3] = cell_43; cells[4][4] = cell_44; cells[4][5] = cell_45;
        cells[5][0] = cell_50; cells[5][1] = cell_51; cells[5][2] = cell_52; cells[5][3] = cell_53; cells[5][4] = cell_54; cells[5][5] = cell_55;
    }

    public TextField[][] getCells() {
        return cells;
    }

    private void setupValidation() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int currentRow = row;
                int currentCol = col;
                cells[row][col].textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("[1-6]?")) {
                        Platform.runLater(() -> cells[currentRow][currentCol].setText(""));
                    } else if (!newValue.isEmpty()) {
                        int number = Integer.parseInt(newValue);
                        if (game.isValidMove(currentRow, currentCol, number)) {
                            game.makeMove(currentRow, currentCol, number);
                            Platform.runLater(() -> {
                                cells[currentRow][currentCol].setStyle("-fx-text-fill: #325AAF; -fx-border-color: #435D6C; -fx-border-radius: 0;");
                                cells[currentRow][currentCol].setEditable(false);
                            });
                            checkWinCondition();
                        } else {
                            Platform.runLater(() -> cells[currentRow][currentCol].setStyle("-fx-text-fill: #EF4B4C; -fx-border-color: #435D6C; -fx-border-radius: 0;"));
                        }
                    }
                });
            }
        }
    }

    @FXML
    public void handleHelp(ActionEvent event) {
        if (help_counter >= 6) {
            buttonHelp.setDisable(true);
            showMessage("Has gastado todas tus ayudas :(");
            return;
        }

        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (cells[row][col].getText().isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (emptyCells.isEmpty()) {
            showMessage("No hay celdas vacías para ayudarte.");
            return;
        }

        Random random = new Random();
        int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
        int row = cell[0];
        int col = cell[1];

        int number = game.getSolution()[row][col];
        cells[row][col].setText(String.valueOf(number));
        Platform.runLater(() -> cells[row][col].setStyle("-fx-text-fill: #000; -fx-border-color: #435D6C; -fx-border-radius: 0;"));
        game.makeMove(row, col, number);

        help_counter++;
        showMessage("Ayuda utilizada en celda [" + head_position[col] + "][" + (row + 1) + "]: Número sugerido => " + number);
    }

    public void checkWinCondition() {
        if (game.isComplete()) {
            showMessage("¡Enhorabuena! Felicitaciones, has ganado el Sudoku :)");
            buttonHelp.setDisable(true);
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    cells[row][col].setStyle("-fx-text-fill: gold; -fx-border-color: #435D6C; -fx-border-radius: 0;");
                    cells[row][col].setEditable(false);
                }
            }
        }
    }

    private void resetGame() {
        game = new Game();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                cells[row][col].setText("");
                cells[row][col].setEditable(true);
                cells[row][col].setStyle("");
            }
        }
        help_counter = 0;
        buttonHelp.setDisable(false);
        helpText.setText("");
    }

    private void showMessage(String message) {
        helpText.setText(message);
    }


    public IGame getGame() {
        return game;
    }

}
