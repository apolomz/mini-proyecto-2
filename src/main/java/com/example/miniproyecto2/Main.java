/**
 * MiniProyecto #2 Sudoku 6x6
 * Creators: Jhoan Sebastian Fernandez - 2222772, Daniel Cardenas - 2024076
 */
package com.example.miniproyecto2;

import com.example.miniproyecto2.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class to launch the Sudoku application.
 */
public class Main extends Application {
    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the primary stage and displays the welcome screen.
     *
     * @param primaryStage The primary stage for this application.
     * @throws IOException If the welcome stage cannot be initialized.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }
}
