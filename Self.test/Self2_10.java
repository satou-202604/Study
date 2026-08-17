/**
 * 課題内容
 *
 * 本課題では、
 * ・継承
 * ・オーバーロード
 * ・オーバーライド
 * の使い方を学んでいきましょう。
 *
 * 問①〜問⑦まであります。
 * 指定されたクラス名・メソッド名を守って記述してください。
 *
 */

// 問①
// Animalクラスを作成してください。
class Animal {

    // 問②
    // speakメソッドを作成してください。
    // 「動物が鳴いています」と表示してください。
    public void speak() {
        System.out.println("動物が鳴いています");
    }

}


// 問③
// DogクラスをAnimalクラスから継承してください。
class Dog extends Animal {

    // 問④
    // speakメソッドをオーバーライドしてください。
    // 「ワンワン！」と表示してください。
    @Override
    public void speak() {
        System.out.println("ワンワン！");
    }


    // 問⑤
    // speakメソッドをオーバーロードしてください。
    // 引数 name を受け取り、
    // 「{name}がワンワン！と鳴いています」
    // と表示してください。
    public void speak(String name) {
        System.out.println(name + "がワンワン！と鳴いています");
    }


}

public class Self2_10 {

    public static void main(String[] args) {

        // 問⑥
        // Dogクラスをインスタンス化してください。
        Dog dog = new Dog();



        // 問⑦
        // speakメソッドを2種類呼び出してください。
        //
        // 実行結果：
        // ワンワン！
        // ポチがワンワン！と鳴いています

        dog.speak();
        dog.speak("ポチ");

    }
}