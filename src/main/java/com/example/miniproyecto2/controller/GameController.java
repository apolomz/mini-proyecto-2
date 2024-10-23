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

/**
 *  Controller class for managing the game's UI and logic.
 */
public class GameController {

    /**
     * @param rows The list of rows for managing the grid's state.
     * @param cols The list of columns for managing the grid's state.
     * @param block The list of blocks for 2x3 sections of the grid.
     * @param game The instance of the Game class managing the game's logic.
     * @param help_counter A counter that keeps track of how many help options the player has used.
     * @param head_position The array storing the column headers for display purposes.
     * @param cells A 2D array of TextField elements representing the 6x6 Sudoku grid.
     * @param helpText The label used to display messages related to the help functionality.
     * @param buttonHelp The button used to request a help suggestion.
     * @param resetButton The button to reset the game.
     */

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

    /**
     * Handles the reset button action to reset the game.
     *
     * @param event The action event triggered by the button.
     */
    @FXML
    void handleReset(ActionEvent event) {
        resetGame();
        helpText.setText("");
    }

    /**
     * Initializes the controller and sets up the grid and validation.
     */
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
        game = new Game();

        initializeTextFieldGrid();
        setupValidation();
    }

    /**
     * Initializes the text field grid.
     */
    @FXML
    private void initializeTextFieldGrid() {
        cells[0][0] = cell_00; cells[0][1] = cell_01; cells[0][2] = cell_02; cells[0][3] = cell_03; cells[0][4] = cell_04; cells[0][5] = cell_05;
        cells[1][0] = cell_10; cells[1][1] = cell_11; cells[1][2] = cell_12; cells[1][3] = cell_13; cells[1][4] = cell_14; cells[1][5] = cell_15;
        cells[2][0] = cell_20; cells[2][1] = cell_21; cells[2][2] = cell_22; cells[2][3] = cell_23; cells[2][4] = cell_24; cells[2][5] = cell_25;
        cells[3][0] = cell_30; cells[3][1] = cell_31; cells[3][2] = cell_32; cells[3][3] = cell_33; cells[3][4] = cell_34; cells[3][5] = cell_35;
        cells[4][0] = cell_40; cells[4][1] = cell_41; cells[4][2] = cell_42; cells[4][3] = cell_43; cells[4][4] = cell_44; cells[4][5] = cell_45;
        cells[5][0] = cell_50; cells[5][1] = cell_51; cells[5][2] = cell_52; cells[5][3] = cell_53; cells[5][4] = cell_54; cells[5][5] = cell_55;
    }

    /**
     * Gets the grid of cells.
     *
     * @return The grid of TextField objects.
     */
    public TextField[][] getCells() {
        return cells;
    }

    /**
     * Sets up validation for the cells in the grid.
     */
    private void setupValidation() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                cells[row][col].textProperty().addListener(new CellValidationAdapter(this, row, col));
            }
        }
    }


    /**
     * Handles the help button action to suggest a number for an empty cell.
     *
     * @param event The action event triggered by the button.
     */
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

    /**
     * Checks if the player has won the game.
     */
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

    /**
     * Resets the game state and UI.
     */
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

    /**
     * Displays a message in the help text label.
     *
     * @param message The message to display.
     */
    private void showMessage(String message) {
        helpText.setText(message);
    }


    /**
     * Gets the current game instance.
     *
     * @return The game instance.
     */
    public IGame getGame() {
        return game;
    }

}
