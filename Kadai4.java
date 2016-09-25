import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Kadai4 {

	public static void main(String args[]) {

		Kadai4_dao dao = new Kadai4_dao();
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "";

		try {

			// 接続
			con = dao.connectDB(con);
			if (con != null) {

				// SELECT文の実行
				sql = "SELECT * FROM " + Kadai4_const.TABLE_BOOK + ";";
				rs = dao.selectDB(con, stm, sql);

				// SELECT結果の表示
				String result;
				while (rs.next()) {
					result = Kadai4_const.BOOK_COLUMN_NAME_ID + "：" + rs.getString(Kadai4_const.BOOK_COLUMN_ID);
					result += ", " + Kadai4_const.BOOK_COLUMN_NAME_GENRE + "："
							+ rs.getString(Kadai4_const.BOOK_COLUMN_GENRE);
					result += ", " + Kadai4_const.BOOK_COLUMN_NAME_TITLE + "："
							+ rs.getString(Kadai4_const.BOOK_COLUMN_TITLE);
					result += ", " + Kadai4_const.BOOK_COLUMN_NAME_PRICE + "："
							+ rs.getString(Kadai4_const.BOOK_COLUMN_PRICE);
					result += ", " + Kadai4_const.BOOK_COLUMN_NAME_AUTHOR + "："
							+ rs.getString(Kadai4_const.BOOK_COLUMN_AUTHOR);
					result += ", " + Kadai4_const.BOOK_COLUMN_NAME_PUBLISHER + "："
							+ rs.getString(Kadai4_const.BOOK_COLUMN_PUBLISHER);
					result += ", " + Kadai4_const.BOOK_COLUMN_NAME_ENRTY_DATE + "："
							+ rs.getString(Kadai4_const.BOOK_COLUMN_ENRTY_DATE);
					result += ", " + Kadai4_const.BOOK_COLUMN_NAME_UPDATE_DATE + "："
							+ rs.getString(Kadai4_const.BOOK_COLUMN_UPDATE_DATE);

					System.out.println("取得結果 -> " + result);
				}
			}

		} catch (Exception e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.getMessage());

		} finally {
			// クローズ
			dao.closeDB(con, stm, rs);
		}

	}

}
