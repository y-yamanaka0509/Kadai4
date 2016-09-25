import java.sql.Connection;

public class Kadai4 {

	public static void main(String args[]) {
		Kadai4_dao dao = new Kadai4_dao();

		Connection con = null;

		try {
			// 接続
			dao.connectDB(con);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// クローズ
			dao.closeDB(con);
		}
	}

}
