package task3_16;

public class Student extends Person {
    private String school;

    public Student(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    // オーバーライド
    @Override
    public void introduce() {
        System.out.println("Student: " + name + " (" + age + ") / " + school);
    }
}