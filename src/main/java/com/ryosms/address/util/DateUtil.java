package com.ryosms.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Dateを扱うヘルパークラス
 */
public class DateUtil {

    /**
     * 日付の変換に使用するパターン
     */
    private static final String DATE_PATTERN = "yyyy/MM/dd";

    /**
     * 日付のフォーマッタ
     */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * パラメータの日付を文字列変換して返す。
     * フォーマット後の形式は{@link com.ryosms.address.util.DateUtil#DATE_PATTERN}で定義
     *
     * @param date 文字列変換する日付
     * @return 変換後文字列
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * {@link com.ryosms.address.util.DateUtil#DATE_PATTERN}形式の日付文字列を{@link java.time.LocalDate}に変換する
     * 変換できない場合はnullが返る
     *
     * @param dateString 変換対象の日付文字列
     * @return 日付オブジェクト。ただし変換不可の場合はnull
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * 文字列が日付変換可能かチェックする
     *
     * @param dateString 変換対象の文字列
     * @return 日付変換可能の場合はtrue
     */
    public static boolean validDate(String dateString) {
        return parse(dateString) != null;
    }
}
