package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

    /* 定数 */
    /** ドライバーのクラス名 */
    private static final String POSTGRES_DRIVER =
            "org.postgresql.Driver";

    /** JDBC接続先情報 */
    private static final String JDBC_CONNECTION =
            "jdbc:postgresql://localhost:5432/lesson_db";

    /** ユーザー名 */
    private static final String USER = "postgres";

    /** パスワード */
    private static final String PASS = "5927";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {

            // JDBCドライバ読み込み
            Class.forName(POSTGRES_DRIVER);

            // DB接続
            connection =
                    DriverManager.getConnection(
                            JDBC_CONNECTION,
                            USER,
                            PASS);

            // Statement作成
            statement = connection.createStatement();

            // INSERT文
            String SQL =
                    "INSERT INTO SHOHIN_TB " +
                    "(SHOHIN_ID, SHOHIN_NAME, TANKA) " +
                    "VALUES('021','SHOHIN021',2100)";

            // SQL実行
            statement.executeUpdate(SQL);

            System.out.println("INSERT成功");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();
            }

            System.out.println("DB接続を終了します");
        }
    }
}