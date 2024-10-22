package com.example.miniproyecto2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {

    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto2/welcome-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1280, 780);
        setScene(scene);
        setTitle("Sudoku 6x6");
        getIcons().add(new Image(String.valueOf(
                getClass().getResource("/com/example/miniproyecto2/favicon.png"))));
        setResizable(false);
        show();
    }

    // Singleton pattern for creating only one instance of WelcomeStage
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    // Method to get the instance of WelcomeStage
    public static WelcomeStage getInstance() throws IOException {
        if (WelcomeStageHolder.INSTANCE == null) {
            WelcomeStageHolder.INSTANCE = new WelcomeStage();
        }
        return WelcomeStageHolder.INSTANCE;
    }

    // Method to delete the instance and close the stage
    public static void deleteInstance() {
        if (WelcomeStageHolder.INSTANCE != null) {
            WelcomeStageHolder.INSTANCE.close();
            WelcomeStageHolder.INSTANCE = null;
        }
    }
}
