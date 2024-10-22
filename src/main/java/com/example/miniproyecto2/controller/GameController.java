package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.model.Game;
import com.example.miniproyecto2.model.IGame;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    @FXML
    private Button buttonHelp;

    @FXML
    private TextField cell_00;

    @FXML
    private TextField cell_01;

    @FXML
    private TextField cell_02;

    @FXML
    private TextField cell_03;

    @FXML
    private TextField cell_04;

    @FXML
    private TextField cell_05;

    @FXML
    private TextField cell_10;

    @FXML
    private TextField cell_11;

    @FXML
    private TextField cell_12;

    @FXML
    private TextField cell_13;

    @FXML
    private TextField cell_14;

    @FXML
    private TextField cell_15;

    @FXML
    private TextField cell_20;

    @FXML
    private TextField cell_21;

    @FXML
    private TextField cell_22;

    @FXML
    private TextField cell_23;

    @FXML
    private TextField cell_24;

    @FXML
    private TextField cell_25;

    @FXML
    private TextField cell_30;

    @FXML
    private TextField cell_31;

    @FXML
    private TextField cell_32;

    @FXML
    private TextField cell_33;

    @FXML
    private TextField cell_34;

    @FXML
    private TextField cell_35;

    @FXML
    private TextField cell_40;

    @FXML
    private TextField cell_41;

    @FXML
    private TextField cell_42;

    @FXML
    private TextField cell_43;

    @FXML
    private TextField cell_44;

    @FXML
    private TextField cell_45;

    @FXML
    private TextField cell_50;

    @FXML
    private TextField cell_51;

    @FXML
    private TextField cell_52;

    @FXML
    private TextField cell_53;

    @FXML
    private TextField cell_54;

    @FXML
    private TextField cell_55;

    @FXML
    private TextField[][] cells  = new TextField[6][6];

    private List<List<Integer>> rows;
    private List<List<Integer>> cols;
    private List<List<Integer>> block;

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
        validation();
        cells = new TextField[6][6];
        initializeTextFieldGrid();
        setupValidation();
        initializeTextFieldGrid();
        setupValidation();
    }

    @FXML
    private void initializeTextFieldGrid() {
        // Manually map each FXML field to the array
        cells[0][0] = cell_00; cells[0][1] = cell_01; cells[0][2] = cell_02; cells[0][3] = cell_03; cells[0][4] = cell_04; cells[0][5] = cell_05;
        cells[1][0] = cell_10; cells[1][1] = cell_11; cells[1][2] = cell_12; cells[1][3] = cell_13; cells[1][4] = cell_14; cells[1][5] = cell_15;
        cells[2][0] = cell_20; cells[2][1] = cell_21; cells[2][2] = cell_22; cells[2][3] = cell_23; cells[2][4] = cell_24; cells[2][5] = cell_25;
        cells[3][0] = cell_30; cells[3][1] = cell_31; cells[3][2] = cell_32; cells[3][3] = cell_33; cells[3][4] = cell_34; cells[3][5] = cell_35;
        cells[4][0] = cell_40; cells[4][1] = cell_41; cells[4][2] = cell_42; cells[4][3] = cell_43; cells[4][4] = cell_44; cells[4][5] = cell_45;
        cells[5][0] = cell_50; cells[5][1] = cell_51; cells[5][2] = cell_52; cells[5][3] = cell_53; cells[5][4] = cell_54; cells[5][5] = cell_55;
    }

    private void validation(){
        for (int row=0; row < 6; row++) {
            for (int col=0; col < 6; col++) {
                int currentRow = row;
                int currentCol = col;

                cells[row][col].textProperty().addListener((observable, oldValue, newValue) -> {
                    if(!newValue.matches("[1-6]")){
                        cells[currentRow][currentCol].setText("");
                    }
                    else {
                        int newNumber = Integer.parseInt(newValue);

                    }
                });
            }
        }

    }

    private IGame game;
    private int helpCounter = 0;

    public GameController() {
        game = new Game(); // Initialize the game logic
    }


    // Set up validation and event handling for the grid
    private void setupValidation() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int currentRow = row;
                int currentCol = col;

                cells[row][col].textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("[1-6]?")) {
                        // Use runLater to avoid modifying the text during the listener call
                        Platform.runLater(() -> cells[currentRow][currentCol].setText(""));
                    } else if (!newValue.isEmpty()) {
                        int number = Integer.parseInt(newValue);
                        if (game.isValidMove(currentRow, currentCol, number)) {
                            game.makeMove(currentRow, currentCol, number); // Make the move
                            // Update the cell style for valid input
                            Platform.runLater(() -> cells[currentRow][currentCol].setStyle("-fx-border-color: green;"));
                            checkWinCondition();  // Check if the game is complete after every move
                        } else {
                            // If the move is invalid, show a red border
                            Platform.runLater(() -> cells[currentRow][currentCol].setStyle("-fx-border-color: red;"));
                        }
                    }
                });
            }
        }
    }

    // Handle the help button to suggest a valid number
    @FXML
    public void handleHelp(ActionEvent event) {
        if (helpCounter >= 30) {
            showAlert("Help Limit", "You have already used the maximum of 5 helps.");
            return; // Don't provide more than 5 helps
        }

        // Find an empty cell
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (cells[row][col].getText().isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (emptyCells.isEmpty()) {
            showAlert("No Empty Cells", "No more empty cells are available.");
            return; // No empty cells to suggest help for
        }

        // Select a random empty cell
        Random random = new Random();
        int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
        int row = cell[0];
        int col = cell[1];

        // Get the solution for that cell
        int number = game.getSolution()[row][col];

        // Suggest the valid number and update the grid
        cells[row][col].setText(String.valueOf(number));
        cells[row][col].setStyle("-fx-border-color: green;"); // Highlight the cell
        game.makeMove(row, col, number);
        helpCounter++; // Increment help counter
        showAlert("Help", "Suggested number " + number + " for cell [" + row + "," + col + "].");
    }

    // Helper method to show alerts
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Check if the game is complete and show a win message
    // Check if the game is complete and show a win message
    private void checkWinCondition() {
        if (game.isComplete()) {
            showAlertAndExit("Victory!", "Congratulations, you have completed the Sudoku!");
        }
    }

    // Show the winning alert and then exit the application
    private void showAlertAndExit(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show(); // Show the alert non-blocking

        // Use PauseTransition to delay the exit by 2 seconds (to allow the user to read the message)
        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Adjust duration as needed
        pause.setOnFinished(event -> {
            Platform.exit(); // Exit the application after the pause
        });
        pause.play();
    }


}
