import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Kadai4 {

	public static void main(String args[]) {

		Connection con = null;
		boolean isConnected = false;

		// 接続
		try {
			con = DriverManager.getConnection(Kadai4_const.SQL_URL, Kadai4_const.SQL_USER, Kadai4_const.SQL_PASS);
			System.out.println("MySQLへの接続に成功しました。");
			isConnected=true;
		} catch (Exception e) {
			System.out.println("MySQLへの接続に失敗しました。");
			System.out.println(e.getMessage());
		}

		// クローズ
		if (isConnected) {
			try {
				con.close();
				System.out.println("MySQLのクローズに成功しました。");
			} catch (SQLException e) {
				System.out.println("MySQLのクローズに失敗しました。");
				System.out.println(e.getMessage());
			}
		}

	}

}
