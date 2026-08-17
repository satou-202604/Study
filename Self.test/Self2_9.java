/**
 * 課題内容
 *
 * 本課題では、import（インポート）の使い方を学んでいきましょう。
 * 問①〜問⑤まであります。
 * 指定されたクラス名・メソッド名を守って記述してください。
 *
 */

// 問①
// Scannerクラスをインポートしてください。
import java.util.Scanner;


// 問②
// Randomクラスをインポートしてください。
import java.util.Random;


public class Self2_9 {

    public static void main(String[] args) {

        // 問③
        // Scannerクラスを使用して scanner を作成してください。
        Scanner scanner = new Scanner(System.in);


        // 問④
        // Randomクラスを使用して random を作成してください。
        Random random = new Random();



        // 問⑤
        // キーボードから名前を入力し、
        // 「{名前}さんのラッキーナンバーは{ランダムな数}です」
        // と表示してください。
        //
        // ※ ラッキーナンバーは1〜100の乱数
        System.out.print("名前を入力してください：");
        String name = scanner.nextLine();
        int luckyNumber = random.nextInt(100) + 1;
        System.out.println(name + "さんのラッキーナンバーは" + luckyNumber + "です");

    }
}
