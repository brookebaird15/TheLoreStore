package com.example.thelorestore;

import com.example.thelorestore.Database.Database;
import com.example.thelorestore.Scenes.LoginScene;
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
        /** TODO
         * Update the title to better represent the application use
         */
        mainStage.setTitle("The Lore Store: Inventory Manager");
        mainStage.show();
    }
}
