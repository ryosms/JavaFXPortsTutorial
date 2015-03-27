package com.ryosms.address.view;

import com.ryosms.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 誕生日の統計画面用コントローラ
 */
public class BirthdayStatisticsController {
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 英語の月名を取得する
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();

        // ObservableListに変換する
        monthNames.addAll(Arrays.asList(months));

        // 月名を横軸のカテゴリとして設定する
        xAxis.setCategories(monthNames);
    }

    /**
     * 統計として表示する人物データをセットする
     *
     * @param persons
     */
    public void setPersonData(List<Person> persons) {
        // 誕生日の月を取得する
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        // 各月のXYChart.Dataオブジェクトを生成してseriesにセットする
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(series);
    }

}
