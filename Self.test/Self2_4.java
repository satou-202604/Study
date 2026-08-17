/**
 * 課題内容
 *
 * 本課題では、制御文（条件分岐）の使い方を学んでいきましょう。
 * 問①から問⑤まであります。
 * 指定された値・変数名を守って記述してください。
 *
 */

public class Self2_4 {

    public static void main(String[] args) {
    

        // =========================
        // 問①
        // 変数 score に 75 を代入し、
        // 80以上なら「合格」
        // それ以外は「不合格」
        // と表示してください。
        // =========================

        int score = 75;
        //String型の変数を3つ用意し、表示したいメッセージを記載する
        // String allowed = "合格";
        // String notAllowed = "不合格";

        // 制御文
        String allowed = "合格";
        String notAllowed = "不合格";
        // 80以上の場合は、"合格"と表示
         if (score >= 80) {
           System.out.println(allowed);

        // それ以外の場合は、"不合格"と表示
        } else {
            System.out.println(notAllowed);
    }


        // =========================
        // 問②
        // 変数 num に 10 を代入し、
        // 偶数なら「偶数です」
        // 奇数なら「奇数です」
        // と表示してください。
        // =========================

        int num = 10;

         if (num % 2 == 0) {
            System.out.println("偶数です");
        } else {
            System.out.println("奇数です");
        }


        // =========================
        // 問③
        // 変数 age に 20 を代入し、
        //
        // 20以上なら
        // 「成人です」
        //
        // それ以外なら
        // 「未成年です」
        //
        // と表示してください。
        // =========================

        int age = 20;

        if (age >= 20) {
            System.out.println("成人です");
        } else {
            System.out.println("未成年です");
        }


        // =========================
        // 問④
        // 変数 signal に "青" を代入し、
        //
        // "青"なら「進め」
        // "黄"なら「注意」
        // "赤"なら「止まれ」
        //
        // と表示してください。
        // =========================

        String signal = "青";

        if (signal.equals("青")) {
            System.out.println("進め");
        } else if (signal.equals("黄")) {
            System.out.println("注意");
        } else if (signal.equals("赤")) {
            System.out.println("止まれ");
        }

        // =========================
        // 問⑤
        // 変数 money に 5000 を代入し、
        //
        // 10000以上なら「高額」
        // 5000以上なら「普通」
        // それ以外なら「不足」
        //
        // と表示してください。
        // =========================

        int money = 5000;

        if (money >= 10000) {
            System.out.println("高額");
        } else if (money >= 5000) {
            System.out.println("普通");
        } else {
            System.out.println("不足");
        }


    }

}
