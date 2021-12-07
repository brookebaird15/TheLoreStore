package com.example.thelorestore.Scenes;

import com.example.thelorestore.Panes.LoginPane;
import javafx.scene.Scene;

import static com.example.thelorestore.Launcher.stylesheet;

public class LoginScene extends Scene {
    public LoginScene() {
        super(new LoginPane(), 1024, 769);
        this.getStylesheets().add(stylesheet);
    }
}
