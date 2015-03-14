package com.ryosms.address;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Created by ryosms on 2015/03/14.
 */
public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(new StackPane(new Label("Hello world!"))
                , bounds.getWidth(), bounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }
}
