import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Kadai4_dao {

	private final String SQL_URL = "jdbc:mysql://localhost/test";
	private final String SQL_USER = "root";
	private final String SQL_PASS = "rsp3swte";

	// 接続
	public boolean connectDB(Connection con) {
		try {
			con = DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASS);
			System.out.println("MySQLに接続できました。");
		} catch (Exception e) {
			System.out.println("MySQLに接続できませんでした。");
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	// クローズ
	public boolean closeDB(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("MySQLのクローズに失敗しました。");
				System.out.println(e.getMessage());
				return false;
			}
		}
		return true;
	}

}
