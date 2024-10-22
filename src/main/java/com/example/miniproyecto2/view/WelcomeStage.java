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

    private static class WelcomeStageHolder{
        private static WelcomeStage INSTANCE;
    }

    public static WelcomeStage getInstance() throws IOException{
        WelcomeStageHolder.INSTANCE =
                WelcomeStageHolder.INSTANCE != null ?
                        WelcomeStageHolder.INSTANCE : new WelcomeStage();
        return WelcomeStageHolder.INSTANCE;
    }

    public static void deleteInstance(){
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }
}
