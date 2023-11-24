module com.example.proj1_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proj1_1 to javafx.fxml;
    exports com.example.proj1_1;
}