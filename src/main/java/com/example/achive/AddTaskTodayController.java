package com.example.achive;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.PixelFormat;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AddTaskTodayController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField aim_imput;

    @FXML
    private TextField exp_input;

    @FXML
    private AnchorPane btn_anc;

    @FXML
    private Button cancel_button;

    @FXML
    private Button create_button;

    @FXML
    private DatePicker date_input;

    @FXML
    private RadioButton exp_use;

    @FXML
    private AnchorPane set_exp_num;

    private String name;
    private String date;
    private int exp;
    public int getExp(){return exp;}
    public String getName() {
        return name;
    }

    public String getDate(){
        return date;
    }

    @FXML
    void initialize() {
        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancel_button.getScene().getWindow().hide();
            }
        });

        exp_use.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                set_exp_num.setVisible(exp_use.isSelected());
            }
        });

        create_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (aim_imput.getText().isEmpty()){
                    name = null;
                }else {

                    name = aim_imput.getText();
                }

                LocalDate selectedDate = date_input.getValue();
                if (selectedDate != null) {
                    date = formatDate(selectedDate);
                }else {
                    date = "Нет даты";
                }

                if (exp_use.isSelected() && exp_input.getText() != null){
                    try {exp = Integer.parseInt(exp_input.getText());}catch (NumberFormatException e){
                        showErrorAlert("Неправильный ввод", "В строке баллов должны быть только числа.");
                    }
                }
                if (exp_use.isSelected() && exp_input.getText().isEmpty()) {
                    exp = 0;
                }

                create_button.getScene().getWindow().hide();
            }
        });
    }
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private String formatDate(LocalDate date) {
        // Define a date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Format the date as a string
        return date.format(formatter);
    }

}
