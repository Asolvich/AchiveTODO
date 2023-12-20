module com.example.achive {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.achive to javafx.fxml;
    exports com.example.achive;
}