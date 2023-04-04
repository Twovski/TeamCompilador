package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Menu.fxml"));
        Scene scene = new Scene(loader.load());

        stage.getIcons().add(new Image("C:/Imagenes/Ventas/Icon.png"));
        stage.setResizable(false);
        stage.setTitle("Proyecto");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

