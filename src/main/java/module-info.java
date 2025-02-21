module com.example.module_3_assignment_03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.module_3_assignment_03 to javafx.fxml;
    exports com.example.module_3_assignment_03;
}