/**
 * 課題内容
 *
 * 本課題では、演算子の使い方を学んでいきましょう。
 * 問①から問⑦まであります。
 * 指定された数値、式、変数名を守って記述して下さい。
 *
 */

public class Self2_3 {

    public static void main(String[] args) {

        // 問①
        // int型変数 num1 に「10」を代入してください。
        int num1 = 10;

        // 問②
        // int型変数 num2 に「5」を代入してください。
        int num2 = 5;

        // 問③
        // num1 と num2 を足した結果を
        // int型変数 addition に代入してください。
        int addition = num1 + num2;

        // 問④
        // num1 から num2 を引いた結果を
        // int型変数 subtraction に代入してください。
        int subtraction = num1 - num2;

        // 問⑤
        // num1 と num2 を掛けた結果を
        // int型変数 multiplication に代入してください。
        int multiplication = num1 * num2;

        // 問⑥
        // num1 を num2 で割った結果を
        // int型変数 division に代入してください。
        int division = num1 / num2;

        // 問⑦
        // num1 を num2 で割った余りを
        // int型変数 remainder に代入してください。
        int remainder = num1 % num2;



        // ↓ 出力確認用（変更しない）
        System.out.println("addition : " + addition);
        System.out.println("subtraction : " + subtraction);
        System.out.println("multiplication : " + multiplication);
        System.out.println("division : " + division);
        System.out.println("remainder : " + remainder);

    }
}
