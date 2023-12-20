package com.example.achive;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    private int pac_num = 0;
    private boolean isToday = false;
    private int exp_all = 0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_task;

    @FXML
    private Button add_task_1;


    @FXML
    private Text label_1;


    @FXML
    private AnchorPane pac_layer_1;


    @FXML
    private VBox tasks_box_1;

    @FXML
    private Button cittat_button;

    @FXML
    private Button done_task_button;

    @FXML
    private Text none_pac_text;

    @FXML
    private ToggleButton other_pac_button;

    @FXML
    public VBox tasks_box;

    @FXML
    private AnchorPane today_layer;

    @FXML
    private Button today_task_button;

    @FXML
    private VBox done_box;

    @FXML
    private AnchorPane done_layer;

    @FXML
    private Text earned_exp;

    @FXML
    private Button clear_all;

    @FXML
    private VBox addit_pac_box;

    @FXML
    private Button new_pac_btn;
    @FXML
    void initialize() {
        other_pac_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(other_pac_button.isSelected()){
                    addit_pac_box.setVisible(true);
                } else {addit_pac_box.setVisible(false);}
            }
        });

        new_pac_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader  = new FXMLLoader();
                loader.setLocation(getClass().getResource("add-new-pac.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.showAndWait();

                AddNewPacController addNewPacController = loader.getController();
                if (addNewPacController.getName_pac() != null && pac_num < 1) {
                    pac_num += 1;
                    String pac_name = addNewPacController.getName_pac();
                    Button pac = new Button(pac_name);
                    pac.setPrefHeight(30);
                    pac.setPrefWidth(125);
                    addit_pac_box.getChildren().add(pac);

                    pac.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (today_layer.isVisible() || done_layer.isVisible()){
                                today_layer.setVisible(false);
                                done_layer.setVisible(false);
                            }
                            label_1.setText(pac_name);
                            pac_layer_1.setVisible(true);

                            add_task_1.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    FXMLLoader loader  = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("add-task.fxml"));
                                    try {loader.load();} catch (IOException e) {throw new RuntimeException(e);}
                                    Parent root = loader.getRoot();
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(root));
                                    stage.setResizable(false);
                                    stage.showAndWait();

                                    AddTaskController addTaskController = loader.getController();

                                    if (addTaskController.getName() != null) {
                                        String name = addTaskController.getName();
                                        String date = addTaskController.getDate();
                                        ToolBar task_tool = new ToolBar();
                                        Text aim_txt = new Text(name);
                                        Text date_txt = new Text("Дата: " + date);
                                        Button btn_to_done = new Button("Выполнено");
                                        Button btn_delete = new Button("Удалить");
                                        task_tool.getItems().addAll(aim_txt, date_txt, btn_to_done, btn_delete);
                                        tasks_box_1.getChildren().add(task_tool);

                                        btn_to_done.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                task_tool.getItems().remove(btn_to_done);
                                                task_tool.getItems().remove(btn_delete);
                                                done_box.getChildren().add(task_tool);
                                                tasks_box_1.getChildren().remove(task_tool);
                                            }
                                        });

                                        btn_delete.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                tasks_box_1.getChildren().remove(task_tool);
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });

        done_task_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (today_layer.isVisible()){ today_layer.setVisible(false);}
                if(pac_layer_1.isVisible()){pac_layer_1.setVisible(false);}
                done_layer.setVisible(true);
                none_pac_text.setVisible(false);

                clear_all.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        done_box.getChildren().clear();
                    }
                });
            }
        });

        today_task_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (done_layer.isVisible()){done_layer.setVisible(false);}
                if(pac_layer_1.isVisible()){pac_layer_1.setVisible(false);}
                isToday = true;
                today_layer.setVisible(true);
                none_pac_text.setVisible(false);

            }
        });

        add_task.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isToday){
                    FXMLLoader loader  = new FXMLLoader();
                    loader.setLocation(getClass().getResource("add-task-today.fxml"));
                    try {loader.load();} catch (IOException e) {throw new RuntimeException(e);}

                    AddTaskTodayController  addTaskTodayController = loader.getController();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.showAndWait();

                    if (addTaskTodayController.getName() != null) {
                        String name = addTaskTodayController.getName();
                        String date = addTaskTodayController.getDate();
                        int exp = addTaskTodayController.getExp();
                        ToolBar task_tool = new ToolBar();
                        Text aim_txt = new Text(name);
                        Text date_txt = new Text("Дата: " + date);
                        Text exp_txt = new Text("Баллов за цель: " + exp);
                        Button btn_to_done = new Button("Выполнено");
                        Button btn_delete = new Button("Удалить");
                        task_tool.getItems().addAll(aim_txt, date_txt, exp_txt, btn_to_done, btn_delete);
                        tasks_box.getChildren().add(task_tool);

                        btn_to_done.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                exp_all += exp;
                                earned_exp.setText(""+exp_all);
                                task_tool.getItems().remove(btn_to_done);
                                task_tool.getItems().remove(btn_delete);
                                done_box.getChildren().add(task_tool);
                                tasks_box.getChildren().remove(task_tool);
                            }
                        });

                        btn_delete.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                tasks_box.getChildren().remove(task_tool);
                            }
                        });
                    }

                }
            }
        });

        cittat_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader  = new FXMLLoader();
                loader.setLocation(getClass().getResource("Citata.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.showAndWait();
            }
        });

    }

}
