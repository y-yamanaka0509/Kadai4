import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Kadai4 {

	public static void main(String args[]) {

		Kadai4_dao dao = new Kadai4_dao();
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		// String sql = "";
		// String result;

		try {

			// 接続
			con = dao.connectDB(con);
			if (con != null) {

				//// SELECT文の設定
				// dao.setSQLSelect(con,stm);

				// INSERT文の設定
				dao.setSQLInsert(con, stm);

			}

		} catch (Exception e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.toString());

		} finally {
			// クローズ
			dao.closeDB(con, stm, rs);
		}

	}

}
