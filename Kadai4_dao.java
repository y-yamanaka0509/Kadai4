import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kadai4_dao {

	// 接続
	public Connection connectDB(Connection con) {

		try {
			con = DriverManager.getConnection(Kadai4_const.SQL_URL, Kadai4_const.SQL_USER, Kadai4_const.SQL_PASS);
			System.out.println("MySQLへの接続に成功しました。");
		} catch (Exception e) {
			System.out.println("MySQLへの接続に失敗しました。");
			System.out.println(e.getMessage());
		}
		return con;
	}

	// クローズ
	public boolean closeDB(Connection con, Statement stm, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("MySQLのクローズに失敗しました。");
				System.out.println(e.getMessage());
				return false;
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				System.out.println("MySQLのクローズに失敗しました。");
				System.out.println(e.getMessage());
				return false;
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("MySQLのクローズに失敗しました。");
				System.out.println(e.getMessage());
				return false;
			}
		}
		System.out.println("MySQLのクローズに成功しました。");
		return true;
	}

	// SELECT文の実行
	public ResultSet selectDB(Connection con, Statement stm, String sql) {

		ResultSet rs = null;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("SQLの実行に失敗しました。");
			System.out.println(e.getMessage());
		}

		return rs;
	}

}
