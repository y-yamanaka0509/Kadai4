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
		String result;

		try {

			// 接続
			con = dao.connectDB(con);
			if (con != null) {

				// SELECT文の実行
				sql = "SELECT * FROM " + Kadai4_const.TABLE_BOOK + ";";
				rs = dao.selectDB(con, stm, sql);

				System.out.println("実行SQL文 -> " + sql);

				// SELECT結果の表示
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
				System.out.println();

				// SELECT文の実行
				sql = "SELECT * FROM " + Kadai4_const.TABLE_LIBRARY + ";";
				rs = dao.selectDB(con, stm, sql);

				System.out.println("実行SQL文 -> " + sql);

				// SELECT結果の表示
				while (rs.next()) {
					result = Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_ID + "："
							+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID);
					result += ", " + Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_NAME + "："
							+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME);

					System.out.println("取得結果 -> " + result);
				}
				System.out.println();

				// SELECT文の実行
				sql = "SELECT t2." + Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME + ", COUNT(*) AS COUNT FROM "
						+ Kadai4_const.TABLE_LIBRARY_BOOK + " t1 INNER JOIN " + Kadai4_const.TABLE_LIBRARY
						+ " t2 ON t1." + Kadai4_const.LIBRARY_BOOK_COLUMN_LIBRARY_ID + " = t2."
						+ Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID + " GROUP BY t2."
						+ Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME + ";";
				rs = dao.selectDB(con, stm, sql);

				System.out.println("実行SQL文 -> " + sql);

				// SELECT結果の表示
				while (rs.next()) {
					result = Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_NAME + "："
							+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME);
					result += ", 件数：" + rs.getString("COUNT");

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
