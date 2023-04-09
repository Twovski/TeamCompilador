package com.example.Controllers;

import com.example.Models.Lexico;
import com.example.Models.Token;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LexicoController implements Initializable {
    @FXML
    private TableView<Token> tokenTable;
    @FXML
    private TableColumn<Token, String> tokenTC, lexemaTC;
    ObservableList<Token> observableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableList = FXCollections.observableArrayList();
        tokenTC.setCellValueFactory(new PropertyValueFactory<>("valor"));
        lexemaTC.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tokenTable.setItems(observableList);
    }

    public void refreshTable(TextArea textArea) {
        try{
            observableList.setAll(new ArrayList<>());
            Lexico lexico = new Lexico(textArea.getText());
            ArrayList<Token> tokens = lexico.scan();
            observableList.addAll(tokens);
            tokenTable.refresh();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
