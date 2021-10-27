module com.example.thelorestore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.thelorestore to javafx.fxml;
    exports com.example.thelorestore;
}