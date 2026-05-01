package task3_16;

public class Person {
    protected String name;
    protected int age;

    // コンストラクタ
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // オーバーロード：ageなしのコンストラクタ
    public Person(String name) {
        this.name = name;
        this.age = 0;
    }

    // introduceメソッド（オーバーライド対象）
    public void introduce() {
        System.out.println("Person: " + name + " (" + age + ")");
    }
}
