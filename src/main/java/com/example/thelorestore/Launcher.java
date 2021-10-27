package com.example.thelorestore;

import com.example.thelorestore.Scenes.LoginScene;
import com.example.thelorestore.Scenes.UpdateItemScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {

        Database.getInstance();
        mainStage = stage;
        mainStage.setScene(new LoginScene());
        mainStage.setResizable(false);
        mainStage.setTitle("The Lore Store: Inventory Manager");
        mainStage.show();
    }
}
