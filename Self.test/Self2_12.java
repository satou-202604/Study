/**
 * 課題内容
 *
 * 本課題では、日付操作クラスの使い方を学んでいきましょう。
 * ・LocalDate
 * ・LocalDateTime
 * ・DateTimeFormatter
 *
 * 問①〜問⑥まであります。
 * 指定されたクラス名・メソッド名を守って記述してください。
 *
 */

// 問①
// LocalDateをインポートしてください。
import java.time.LocalDate;



// 問②
// LocalDateTimeをインポートしてください。
import java.time.LocalDateTime;


// 問③
// DateTimeFormatterをインポートしてください。
import java.time.format.DateTimeFormatter;


public class Self2_12 {

    public static void main(String[] args) {

        // 問④
        // 今日の日付を取得してください。
        // 変数名：today
        LocalDate today = LocalDate.now();



        // 問⑤
        // 現在日時を取得してください。
        // 変数名：now
        LocalDateTime now = LocalDateTime.now();



        // 問⑥
        // 下記の形式で表示してください。
        //
        // 実行結果例：
        // 今日の日付：2026-05-16
        // 現在日時：2026/05/16 14:30:25
        //
        // ※ DateTimeFormatterを使用
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        System.out.println("今日の日付：" + today);
        System.out.println("現在日時：" + formattedNow);


    }
}
