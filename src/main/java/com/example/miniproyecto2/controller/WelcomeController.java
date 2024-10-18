package com.example.miniproyecto2.controller;

import com.example.miniproyecto2.view.GameStage;
import com.example.miniproyecto2.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class WelcomeController {
    @FXML
    void HandlePLay(ActionEvent event) throws IOException {
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }

    public void initialize() {
        System.out.println("Controlador cargado");
    }

}
