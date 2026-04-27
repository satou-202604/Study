package study;

import constants.FruitsConstants;
import fruits.Fruits;

public class Task2_19 {
    public static void main(String[] args) {
    	 /* ③ Fruits.javaのprintFruitsメソッドを呼び出しなさい。
    	  *  また、実行結果がターミナルと同じ表示になるように
    	  * printFruitsの引数にはFruitsConstantsに指定されている定数を設定してください。
    	  */
    	Fruits.printFruits(
                FruitsConstants.FRUITS_LEMON_04,
                FruitsConstants.FRUITS_COUNT_20
            );
    }
}