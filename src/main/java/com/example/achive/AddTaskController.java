package com.example.achive;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddTaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField aim_imput;

    @FXML
    private Button cancel_button;

    @FXML
    private Button create_button;

    @FXML
    private DatePicker date_input;

    private String name;
    private String date;
    public String getName() {
        return name;
    }

    public String getDate(){
        return date;
    }

    private String formatDate(LocalDate date) {
        // Define a date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Format the date as a string
        return date.format(formatter);
    }

    @FXML
    void initialize() {
        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancel_button.getScene().getWindow().hide();
            }
        });

        create_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (aim_imput.getText().isEmpty()) {
                    name = null;
                } else {

                    name = aim_imput.getText();
                }

                LocalDate selectedDate = date_input.getValue();
                if (selectedDate != null) {
                    date = formatDate(selectedDate);
                } else {
                    date = "Нет даты";
                }
                create_button.getScene().getWindow().hide();
            }
        });
    }
}