package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ■ データベースに接続するプログラム
 *
 * カリキュラム「JDBCドライバ」を参考に
 * JDBCドライブのjarファイルの設置とビルドパスの追加も忘れないようにしましょう。
 *
 * 問①〜問④までを回答し、データベースと接続してみましょう。
 * カリキュラム「データベースを扱うための準備」を参考にして下さい。
 *
 * 実行結果の提出に関しては、
 * いつも通りソースをコミットしていただきますが、
 * 今回は実行結果のスクリーンショットも合わせて提出していただきます。
 * 画像名はDBAccess.pngとして、3-18フォルダの中に入れ、これまでと同様に提出して下さい。
 *
 */


public class DBAccess {

    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

    private static final String JDBC_CONNECTION =
            "jdbc:postgresql://localhost:5432/lesson_db";

    private static final String USER = "postgres";

    private static final String PASS = "5927";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName(POSTGRES_DRIVER);

            connection = DriverManager.getConnection(
                    JDBC_CONNECTION, USER, PASS);

            statement = connection.createStatement();

            String SQL = "SELECT * FROM SHOHIN_TB";

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {

                String column1 = resultSet.getString("SHOHIN_ID");
                String column2 = resultSet.getString("SHOHIN_NAME");
                int column3 = resultSet.getInt("TANKA");

                System.out.print(column1 + ",");
                System.out.print(column2 + ",");
                System.out.println(column3);
            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {

                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}