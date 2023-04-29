package com.example;

import com.example.Controllers.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Menu.fxml"));
        Scene scene = new Scene(loader.load());
        MenuController menuController = loader.getController();
        stage.getIcons().add(
                new Image(Objects.requireNonNull(getClass().getResource("/resources/images/icono.png")).toExternalForm())
        );
        stage.setTitle("Compilador");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            if(menuController.getLexicoStage() != null)
                menuController.getLexicoStage().close();
        });
        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}

