module Proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example to javafx.fxml;
    opens com.example.Controllers to javafx.fxml;
    opens com.example.Models to javafx.fxml;

    exports com.example;
    exports com.example.Controllers;
    exports com.example.Models;

}