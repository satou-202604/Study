/**
 * 課題内容
 *
 * 本課題では、getterメソッド・setterメソッドの使い方を学んでいきましょう。
 * 問①〜問⑤まであります。
 * 指定された変数名・メソッド名を守って記述してください。
 *
 */


class Employee {

    // 問①
    // フィールド変数を作成してください。
    // ・name（String型）
    // ・age（int型）
    //フィールド変数
    private String name;
    private int age;



    // 問②
    // setterメソッドを作成してください。
    // メソッド名：
    // ・setName
    // ・setAge
public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 問③
    // getterメソッドを作成してください。
    // メソッド名：
    // ・getName
    // ・getAge
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }



}

public class Self2_8 {

    public static void main(String[] args) {

        // 問④
        // Employeeクラスをインスタンス化してください。
        Employee employee = new Employee();


        // 問⑤
        // setterメソッドを使用して下記を代入してください。
        // 名前：田中
        // 年齢：25
        employee.setName("田中");
        employee.setAge(25);

        // getterメソッドを使用して下記のように表示してください。
        // 実行結果：
        // 名前：田中
        // 年齢：25
        System.out.println("名前：" + employee.getName());
        System.out.println("年齢：" + employee.getAge());

    }
} 
    
