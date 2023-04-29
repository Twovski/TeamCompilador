package com.example.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MenuController implements Initializable {
    @FXML
    private BorderPane border;
    @FXML
    private MenuBar bar;
    @FXML
    private MenuItem save, close, lexical;
    private LexicoController lexicoController;
    private Node imageView;
    private TextArea textArea;
    private String path;
    private Stage lexicoStage;
    private final KeyCombination keyCtrlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCtrlN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCtrlO = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyCtrlF4 = new KeyCodeCombination(KeyCode.F4, KeyCombination.CONTROL_DOWN);
    private final KeyCombination keyAltF4 = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        path = null;
        lexicoController = null;
        imageView = border.getCenter();
        save.setDisable(true);
        close.setDisable(true);
        textArea = new TextArea();
        textArea.setFont(new Font("Courier New", 14));
        textArea.setOnKeyReleased(event -> refreshWindowLexico());
    }

    @FXML
    private void keyWindow(KeyEvent event){
        try{
            if(keyCtrlS.match(event)){
                if(imageView == border.getCenter()) {
                    return;
                }
                saveFile();
            }
            if(keyCtrlN.match(event))
                newFile();

            if(keyCtrlO.match(event))
                openFile();

            if(keyCtrlF4.match(event)){
                lexical.setDisable(false);
                closeFile();
            }

            if(keyAltF4.match(event)){
                if(lexicoStage != null)
                    lexicoStage.close();
                actionExit();
            }


        }catch (Exception error){
            throw new RuntimeException();
        }
    }
    @FXML
    private void actionSemantic(){

    }
    @FXML
    private void actionLexical() throws IOException {
        lexical.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Views/Lexico.fxml"));
        Parent root = loader.load();

        lexicoController = loader.getController();
        lexicoController.refreshTable(textArea);

        lexicoStage = new Stage();
        Scene scene = new Scene(root);
        lexicoStage.getIcons().add(new Image("C:/Imagenes/Compilador/icono.png"));
        lexicoStage.setTitle("Lexico");
        lexicoStage.setScene(scene);
        lexicoStage.setOnCloseRequest(windowEvent -> lexical.setDisable(false));
        lexicoStage.show();
    }

    @FXML
    private void actionExit(){
        Stage stage = (Stage) border.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeFile(){
        textArea.setText("");
        refreshWindowLexico();
        border.setCenter(imageView);
        path = null;
        save.setDisable(true);
        close.setDisable(true);
    }

    @FXML
    private void newFile(){
        textArea.setText("");
        border.setCenter(textArea);
        path = null;
        refreshWindowLexico();
        save.setDisable(false);
        close.setDisable(false);
    }

    @FXML
    private void saveFile() throws IOException {
        if(path != null){
            writeFile(new File(path));
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos XD", "*.xd")
        );
        Stage stage = (Stage) bar.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if(file == null)
            return;
        path = file.getAbsolutePath();
        writeFile(file);
    }

    @FXML
    private void openFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos XD", "*.xd")
        );
        Stage stage = (Stage) bar.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if(file == null)
            return;

        path = file.getAbsolutePath();
        textArea.setText("");

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            textArea.appendText(scanner.nextLine() + "\n");
        }
        refreshWindowLexico();
        border.setCenter(textArea);
        save.setDisable(false);
        close.setDisable(false);
    }

    private void writeFile(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(textArea.getText(), 0, textArea.getText().length());
        bufferedWriter.close();
    }

    private void refreshWindowLexico(){
        if(lexicoController == null){
            return;
        }
        lexicoController.refreshTable(textArea);
    }
    public Stage getLexicoStage() {
        return lexicoStage;
    }
}
