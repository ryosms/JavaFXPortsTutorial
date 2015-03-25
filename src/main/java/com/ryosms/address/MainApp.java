package com.ryosms.address;

import com.ryosms.address.model.Person;
import com.ryosms.address.view.PersonEditDialogController;
import com.ryosms.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * Created by ryosms on 2015/03/14.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * ダミーデータを追加する
     */
    public MainApp() {
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }


    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");

        // アイコンをセットする
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResource("/icon/app_icon.png").toString()));

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

        // コントローラにMainAppへの参照を渡す
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * パラメータで指定したPersonの詳細を編集するダイアログを開きます
     * ユーザがOKボタンをクリックした場合、変更内容がパラメータに保存され、trueを返します。
     *
     * @param person 編集対象のPersonオブジェクト
     * @return ユーザーがOKボタンをクリックしたらtrueを返します。それ以外はfalseを返します。
     * @throws IOException
     */
    public boolean showPersonEditDialog(Person person) throws IOException {
        // Person Edit DialogのFXMLを読み込む
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = loader.load();

        // Dialog用のStageを生成する
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Personオブジェクトをコントローラにセットする
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // ダイアログを表示して閉じられるまで待機
        dialogStage.showAndWait();

        return controller.isOkClicked();
    }

    /**
     * 最後に開いていたpersonファイルを設定より取得する
     * 設定はOSで指定されたレジストリから読み込む
     * 設定が存在しない場合はnullを返す
     *
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * 読み込み中のファイルパスをOSのレジストリにセットする
     *
     * @param file 読み込み中ファイル。nullの場合は設定を削除する
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Stageのタイトルを更新する
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("AddressApp");
        }
    }
}
