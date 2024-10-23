package com.example.miniproyecto2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the welcome stage of the Sudoku application.
 * This stage displays the welcome screen before starting the game.
 */
public class WelcomeStage extends Stage {

    /**
     * Constructs a new WelcomeStage and initializes the UI.
     *
     * @throws IOException If the FXML file cannot be loaded.
     */
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto2/welcome-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
        setTitle("Sudoku 6x6");
        getIcons().add(new Image(String.valueOf(
                getClass().getResource("/com/example/miniproyecto2/favicon.png"))));
        setResizable(false);
        show();
    }

    /**
     * Singleton pattern for creating only one instance of WelcomeStage, Internal Class.
     */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    /**
     * Gets the singleton instance of WelcomeStage.
     *
     * @return The instance of WelcomeStage.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static WelcomeStage getInstance() throws IOException {
        if (WelcomeStageHolder.INSTANCE == null) {
            WelcomeStageHolder.INSTANCE = new WelcomeStage();
        }
        return WelcomeStageHolder.INSTANCE;
    }

    /**
     * Deletes the instance of WelcomeStage and closes the stage.
     */
    public static void deleteInstance() {
        if (WelcomeStageHolder.INSTANCE != null) {
            WelcomeStageHolder.INSTANCE.close();
            WelcomeStageHolder.INSTANCE = null;
        }
    }
}
