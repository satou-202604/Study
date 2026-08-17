/**
 * 課題内容
 *
 * 本課題では、変数、メソッド、戻り値の基本的な使い方を学んでいきましょう。
 * 問①から問⑧まであります。
 * 指定された変数名、メソッド名、値を守って記述してください。
 *
 */

public class Self2_7 {

    // 問①
    // int型のメソッド「add」を作成してください。
    // 引数は「a」と「b」です。
    // a + b の結果を returnしてください。
    // 
    public static int add(int a, int b) {
        return a + b;
    }


    // 問②
    // int型のメソッド「sub」を作成してください。
    // 引数は「a」と「b」です。
    // a - b の結果を returnしてください。
     public static int sub(int a, int b) {
        return a - b;
    }



    // 問③
    // String型のメソッド「getMessage」を作成してください。
    // 「計算完了」という文字列をreturnしてください。
    public static String getMessage() {
        return "計算完了";
    }



    public static void main(String[] args) {

        // 問④
        // int型の変数「num1」に「10」を代入してください。
        int num1 = 10;


        // 問⑤
        // int型の変数「num2」に「5」を代入してください。
        int num2 = 5;


        // 問⑥
        // addメソッドを呼び出し、
        // 結果をint型の変数「addResult」に代入してください。
        int addResult = add(num1, num2);



        // 問⑦
        // subメソッドを呼び出し、
        // 結果をint型の変数「subResult」に代入してください。
        int subResult = sub(num1, num2);



        // 問⑧
        // getMessageメソッドを呼び出し、
        // 結果をString型の変数「message」に代入してください。
        String message = getMessage();



        // 出力
        System.out.println("足し算結果：" + addResult);
        System.out.println("引き算結果：" + subResult);
        System.out.println(message);

    }
}