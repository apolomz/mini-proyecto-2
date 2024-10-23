package com.example.miniproyecto2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {

    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto2/game-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        setScene(scene);
        setTitle("Sudoku 6x6");
        getIcons().add(new Image(String.valueOf(
                getClass().getResource("/com/example/miniproyecto2/favicon.png"))));
        setResizable(false);
        show();
    }

    // Singleton pattern for creating only one instance of GameStage
    private static class GameStageHolder{
        private static GameStage INSTANCE;
    }

    // Method to get the instance of GameStage
    public static GameStage getInstance() throws IOException{
        GameStageHolder.INSTANCE =
                GameStageHolder.INSTANCE != null ?
                        GameStageHolder.INSTANCE : new GameStage();
        return GameStageHolder.INSTANCE;
    }

    // Method to delete the instance and close the stage
    public static void deleteInstance(){
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }
}
