package com.example.achive;

import java.io.IOException;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CitataController {

    String [] citatas = {"Работа не волк работа work", "Пока вы отдыхали, Я работал, пока вы работали, Я отдыхал",
                         "Не заполняй список дел, чтобы иметь 100% эффективность без усилий", "Не сделал дело, не сделай еще одно",
                         "Сделай что-то и оно будет сделано"};

    Random random = new Random();
    int randomIndex = random.nextInt(citatas.length);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text citata_text;

    @FXML
    private Button exit_citat;

    @FXML
    void initialize() {
        //assert citata_text != null : "fx:id=\"citata_text\" was not injected: check your FXML file 'Citata.fxml'.";
        citata_text.setText(citatas[randomIndex]);
        exit_citat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exit_citat.getScene().getWindow().hide();
            }
        });

    }

}
