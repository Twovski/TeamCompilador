module Proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example to javafx.fxml;
    opens com.example.Controllers to javafx.fxml;
    opens com.example.Views to javafx.graphics;

    exports com.example.Controllers;
    exports com.example;

}