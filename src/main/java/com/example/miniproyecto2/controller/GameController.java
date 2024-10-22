package com.example.miniproyecto2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

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
        initializeTextfield();
        validation();

    }

    @FXML
    private void initializeTextfield(){
        cells[0][0] = cell_00;
        cells[0][1] = cell_01;
        cells[0][2] = cell_02;
        cells[0][3] = cell_03;
        cells[0][4] = cell_04;
        cells[0][5] = cell_05;
        cells[1][0] = cell_10;
        cells[1][1] = cell_11;
        cells[1][2] = cell_12;
        cells[1][3] = cell_13;
        cells[1][4] = cell_14;
        cells[1][5] = cell_15;
        cells[2][0] = cell_20;
        cells[2][1] = cell_21;
        cells[2][2] = cell_22;
        cells[2][3] = cell_23;
        cells[2][4] = cell_24;
        cells[2][5] = cell_25;
        cells[3][0] = cell_30;
        cells[3][1] = cell_31;
        cells[3][2] = cell_32;
        cells[3][3] = cell_33;
        cells[3][4] = cell_34;
        cells[3][5] = cell_35;
        cells[4][0] = cell_40;
        cells[4][1] = cell_41;
        cells[4][2] = cell_42;
        cells[4][3] = cell_43;
        cells[4][4] = cell_44;
        cells[4][5] = cell_45;
        cells[5][0] = cell_50;
        cells[5][1] = cell_51;
        cells[5][2] = cell_52;
        cells[5][3] = cell_53;
        cells[5][4] = cell_54;
        cells[5][5] = cell_55;
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



    @FXML
    void handleHelp(ActionEvent event) {


    }

}
