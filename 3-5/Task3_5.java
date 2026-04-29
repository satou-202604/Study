/**
* Task3-5 : 課題内容
* 理解/定着課題⑥（クラス/インスタンス/フィールド/メソッド/コンストラクタ/getter,setter）
*
* 問①〜問⑤の空欄を埋めて、指示通りに出力してください。
*/
public class Task3_5 {
    public static void main(String[] args) {
        /*
         * 問①
         * Studentクラスのインスタンスを、コンストラクタを使って作成してください。
         * nameは「Yuki」、scoreは「68」をセットしてください。
         */
        Student student = new Student("Yuki", 68);

        /*
         * 問②
         * setterを使ってscoreを「82」に更新してください。
         */
        student.setScore(82);

        /*
         * 問③
         * showInfoメソッドを呼び出して、名前と点数を表示してください。
         */
        student.showInfo();

        /*
         * 問④
         * isPassメソッドを呼び出して、合否を表示してください。
         * 合格基準は60点以上です。
         */
        System.out.println("合否：" + student.isPass());

        /*
         * 問⑤
         * addScoreメソッドを使ってscoreに「+5」加算し、再度合否を表示してください。
         */
        student.addScore(5);
        System.out.println("合否：" + student.isPass());
    }
}

class Student {
    private String name;
    private int score;

    // 問① コンストラクタを作成してください（name, scoreを受け取る）
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // 問② getter,setterを作成してください
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // 問③ nameとscoreを表示するshowInfoメソッドを作成してください
    public void showInfo() {
        System.out.println("名前：" + name + " / 点数：" + score);
    }

    // 問④ scoreが60以上なら「合格」、それ以外は「不合格」を返すisPassメソッドを作成してください
    public String isPass() {
        if (score >= 60) {
            return "合格";
        } else {
            return "不合格";
        }
    }

    // 問⑤ scoreに指定した点数を加算するaddScoreメソッドを作成してください
    public void addScore(int point) {
        score += point;
    }
}
