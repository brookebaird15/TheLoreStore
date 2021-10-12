module com.example.thelorestore {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thelorestore to javafx.fxml;
    exports com.example.thelorestore;
}