/**
* 課題内容
*
* 本課題では、配列の使い方を学んでいきましょう。
* 問①から問④まであります。
* 指定された値と変数名を守って記述して下さい。
*
*/

public class Self2_2 {

public static void main(String[] args) {
    // ①「東京都」「千葉県」「群馬県」「栃木県」を要素の値（初期値）とする配列prefecturesを作成しなさい。

        String[] prefectures = {
            "東京都",
            "千葉県",
            "群馬県",
            "栃木県"
        };

        // ②配列の要素数を出力してください。
        System.out.println("配列の要素数: " + prefectures.length);

        /* ③下記の値を保持した、要素数3のStringクラスの配列strArrayを作成しなさい。

         * 1番目（先頭）の要素に 「イチゴ」 を代入
         * 2番目の要素に 「ナシ」 を代入
         * 3番目の要素に 「メロン」 を代入
         */

        String[] strArray = new String[3];

        strArray[0] = "イチゴ";
        strArray[1] = "ナシ";
        strArray[2] = "メロン";

        // ④ ③で作成した配列の3番目の要素を出力しなさい。
        System.out.println("strArrayの3番目の要素: " + strArray[2]);

    }
}
