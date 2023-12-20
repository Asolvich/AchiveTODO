package com.example.achive;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddNewPacController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancel_add_btn;

    @FXML
    private Button create_new_pac_btn;

    @FXML
    private TextField pac_name;

    private String name_pac;
    public String getName_pac(){
        return name_pac;
    }

    @FXML
    void initialize() {
        cancel_add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancel_add_btn.getScene().getWindow().hide();
            }
        });

        create_new_pac_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pac_name.getText().isEmpty()){
                    cancel_add_btn.getScene().getWindow().hide();
                }else {
                    name_pac = pac_name.getText();
                }
                cancel_add_btn.getScene().getWindow().hide();
            }
        });
    }

}
