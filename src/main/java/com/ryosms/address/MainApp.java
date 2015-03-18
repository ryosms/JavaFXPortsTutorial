package com.ryosms.address;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ryosms on 2015/03/14.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * RootLayoutを初期化する
     *
     * @throws IOException
     */
    public void initRootLayout() throws IOException {
        // RootLayout用のFXMLを読み込む
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
        rootLayout = loader.load();

        // root layoutをSceneに表示する
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * root layoutにperson overviewを表示する
     */
    public void showPersonOverview() throws IOException {
        // Person Overview用のFXMLを読み込む
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
        AnchorPane personOverview = loader.load();

        // root layoutのcenterにperson overviewを表示する
        rootLayout.setCenter(personOverview);
    }
}
