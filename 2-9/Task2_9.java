public class Task2_9 {

  // ========== 問1：if文用の定数 ==========
  private static final String USER_NAME = "alice";
  private static final String USER_PASSWORD = "alice123";

  private static final String CONST_MSG_SUCCESS = "ログイン成功です。";
  private static final String CONST_MSG_ERROR_NAME = "名前に誤りがあります。";
  private static final String CONST_MSG_ERROR_PASS = "パスワードに誤りがあります。";
  private static final String CONST_MSG_ERROR_INPUT = "入力情報に誤りがあります。";

  // ========== 問2：switch文用の定数 ==========
  private static final String MON = "月曜日";
  private static final String TUE = "火曜日";
  private static final String WED = "水曜日";
  private static final String THU = "木曜日";
  private static final String FRI = "金曜日";
  private static final String SAT = "土曜日";
  private static final String SUN = "日曜日";

  private static final String MSG_WEEKDAY = "平日営業：9:00〜18:00";
  private static final String MSG_SATURDAY = "土曜営業：10:00〜17:00";
  private static final String MSG_SUNDAY = "定休日";
  private static final String MSG_ERROR = "エラー：不正な曜日です";

  public static void main(String[] args) {

    // ========== 問1：if文 ==========
    String name = "alice";
    String pass = "alice123";

    if (name.equals(USER_NAME) && pass.equals(USER_PASSWORD)) {
      // ① 両方正しい
      System.out.println(CONST_MSG_SUCCESS);

    } else if (name.equals(USER_NAME)) {
      // ② 名前のみ正しい
      System.out.println(CONST_MSG_ERROR_PASS);

    } else if (pass.equals(USER_PASSWORD)) {
      // ③ パスワードのみ正しい
      System.out.println(CONST_MSG_ERROR_NAME);

    } else {
      // ④ 両方間違い
      System.out.println(CONST_MSG_ERROR_INPUT);
    }

    // ========== 問2：switch文 ==========
    String dayOfWeek = "水曜日";

    switch (dayOfWeek) {
      case MON:
      case TUE:
      case WED:
      case THU:
      case FRI:
        System.out.println(MSG_WEEKDAY);
        break;

      case SAT:
        System.out.println(MSG_SATURDAY);
        break;

      case SUN:
        System.out.println(MSG_SUNDAY);
        break;

      default:
        System.out.println(MSG_ERROR);
        break;
    }
  }
}

