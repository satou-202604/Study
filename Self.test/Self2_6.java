/**
 * 課題内容
 *
 * 本課題では、インスタンス化、フィールド変数の基本的な使い方を学んでいきましょう。
 * 問①から問⑥まであります。
 * 指定されたクラス名、変数名、値を守って記述してください。
 *
 */

class Student {

    // 問①
    // String型のフィールド変数「name」を作成してください。
    String name;

    // 問②
    // int型のフィールド変数「age」を作成してください。
    int age;

    // 問③
    // String型のフィールド変数「joinDate」を作成してください。
    String joinDate;

}

public class Self2_6 {

    public static void main(String[] args) {

        // 問④
        // Studentクラスをインスタンス化し、
        // 変数名「stu1」で作成してください。
         Student stu1 = new Student();



        // 問⑤
        // stu1のnameに「佐藤明日香」を代入してください。
        // stu1のageに「32」を代入してください。
        // stu1のjoinDateに「2026-04-01」を代入してください。
        stu1.name = "佐藤明日香";
        stu1.age = 32;
        stu1.joinDate = "2026-04-01";



        // 問⑥
        // 以下の形式で出力してください。
        // 名前：佐藤明日香
        // 年齢：32
        // 入社日：2026-04-01
        System.out.println("名前：" + stu1.name);
        System.out.println("年齢：" + stu1.age);
        System.out.println("入社日：" + stu1.joinDate);


    }
}
