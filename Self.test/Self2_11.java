/**
 * 課題内容
 *
 * 本課題では、コレクションフレームワークの使い方を学んでいきましょう。
 * ・ArrayList
 * ・add
 * ・get
 * ・拡張for文
 *
 * 問①〜問⑥まであります。
 * 指定されたクラス名・変数名を守って記述してください。
 *
 */

// 問①
// ArrayListをインポートしてください。
import java.util.ArrayList;


public class Self2_11 {

    public static void main(String[] args) {

        // 問②
        // String型のArrayListを作成してください。
        // 変数名：fruits
        ArrayList<String> fruits = new ArrayList<>();


        // 問③
        // addメソッドを使用して下記を追加してください。
        // ・りんご
        // ・みかん
        // ・バナナ
        fruits.add("りんご");
        fruits.add("みかん");
        fruits.add("バナナ");



        // 問④
        // getメソッドを使用して
        // 「1番目の果物：りんご」
        // と表示してください。
        System.out.println("1番目の果物：" + fruits.get(0));




        // 問⑤
        // 拡張for文を使用して
        // 全ての果物を表示してください。
        //
        // 実行結果：
        // りんご
        // みかん
        // バナナ
        for (String fruit : fruits) {
            System.out.println(fruit);
        }



        // 問⑥
        // fruitsの要素数を表示してください。
        //
        // 実行結果：
        // 要素数：3
        System.out.println("要素数：" + fruits.size());


    }
}