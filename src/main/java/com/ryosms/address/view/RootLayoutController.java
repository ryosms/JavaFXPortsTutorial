package com.ryosms.address.view;

import com.ryosms.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * root layoutのコントローラークラス
 */
public class RootLayoutController {

    private MainApp mainApp;

    /**
     * MainAppへの参照をセットするために呼び出される
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * 空のAddress Bookを作成する
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * 読み込むファイルを指定するFileChooserを開く
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // 拡張子のFilterをセットする
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // file dialogを表示する
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * 現在開いているpersonデータファイルを上書き保存する
     * 開いているファイルがない場合は新規保存処理を行う
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * 保存するファイルを選択するダイアログを表示する
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // 拡張子のフィルターをセットする
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // ファイル保存ダイアログを表示する
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            // 拡張子が正しいか確認する
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }

    /**
     * Aboutダイアログを表示する
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: ryosms\nwebsite: http://blog.livedoor.jp/ryosms/");

        alert.showAndWait();
    }

    /**
     * アプリケーションを終了する
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
