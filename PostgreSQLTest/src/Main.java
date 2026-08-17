import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

    	String url = "jdbc:postgresql://localhost:5432/postgres";

        String user = "postgres";
        String password = "5927";

        try {

            Class.forName("org.postgresql.Driver");

            Connection connection =
                DriverManager.getConnection(
                    url,
                    user,
                    password
                );

            System.out.println("接続成功");

            connection.close();

        } catch (ClassNotFoundException e) {

            System.out.println("ドライバがありません");

        } catch (SQLException e) {

            System.out.println("DB接続失敗");

            e.printStackTrace();
        }
    }
}