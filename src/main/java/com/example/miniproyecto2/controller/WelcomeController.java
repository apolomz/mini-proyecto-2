package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.view.GameStage;
import com.example.miniproyecto2.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller class for managing the welcome stage interactions.
 */
public class WelcomeController {
    /**
     * Handles the play button action to start the game.
     *
     * @param event The action event triggered by the button.
     * @throws IOException If the game stage cannot be opened.
     */
    @FXML
    void HandlePLay(ActionEvent event) throws IOException {
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }

    /**
     * Initializes the controller and outputs a message to the console.
     */
    public void initialize() {
        System.out.println("Controlador cargado");
    }

}
