import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Kadai4_dao {

	// 接続
	public Connection connectDB(Connection con) {

		try {
			con = DriverManager.getConnection(Kadai4_const.SQL_URL, Kadai4_const.SQL_USER, Kadai4_const.SQL_PASS);
			// System.out.println("MySQLへの接続に成功しました。");
		} catch (Exception e) {
			System.out.println("MySQLへの接続に失敗しました。");
			System.out.println(e.toString());
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
				System.out.println(e.toString());
				return false;
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				System.out.println("MySQLのクローズに失敗しました。");
				System.out.println(e.toString());
				return false;
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("MySQLのクローズに失敗しました。");
				System.out.println(e.toString());
				return false;
			}
		}
		// System.out.println("MySQLのクローズに成功しました。");
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
			System.out.println(e.toString());
		}
		return rs;
	}

	// INSERT文の実行
	public int insertDB(Connection con, Statement stm, String sql) {

		int result = 0;
		try {
			stm = con.createStatement();
			result = stm.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQLの実行に失敗しました。");
			System.out.println(e.toString());
		}
		return result;
	}

	// SELECT文の設定（課題4_3）
	public boolean setSQLSelect(Connection con, Statement stm) {

		String sql;
		String result;
		ResultSet rs = null;

		// SELECT文の実行
		sql = "SELECT * FROM " + Kadai4_const.TABLE_BOOK + ";";
		rs = selectDB(con, stm, sql);

		System.out.println("実行SQL文 -> " + sql);

		// SELECT結果の表示
		try {
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
		} catch (SQLException e) {
			System.out.println("SQLの実行に失敗しました。");
			System.out.println(e.toString());
			return false;
		}
		System.out.println();

		// SELECT文の実行
		sql = "SELECT * FROM " + Kadai4_const.TABLE_LIBRARY + ";";
		rs = selectDB(con, stm, sql);

		System.out.println("実行SQL文 -> " + sql);

		// SELECT結果の表示
		try {
			while (rs.next()) {
				result = Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_ID + "："
						+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID);
				result += ", " + Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_NAME + "："
						+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME);

				System.out.println("取得結果 -> " + result);
			}
		} catch (SQLException e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.toString());
			return false;
		}
		System.out.println();

		// SELECT文の実行
		sql = "SELECT t2." + Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME + ", COUNT(*) AS COUNT FROM "
				+ Kadai4_const.TABLE_LIBRARY_BOOK + " t1 INNER JOIN " + Kadai4_const.TABLE_LIBRARY + " t2 ON t1."
				+ Kadai4_const.LIBRARY_BOOK_COLUMN_LIBRARY_ID + " = t2." + Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID
				+ " GROUP BY t2." + Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME + ";";
		rs = selectDB(con, stm, sql);

		System.out.println("実行SQL文 -> " + sql);

		// SELECT結果の表示
		try {
			while (rs.next()) {
				result = Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_NAME + "："
						+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME);
				result += ", 件数：" + rs.getString("COUNT");

				System.out.println("取得結果 -> " + result);
			}
		} catch (SQLException e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.toString());
			return false;
		}

		return true;

	}

	// INSERT文の設定（課題4_4）
	public boolean setSQLInsert(Connection con, Statement stm) {

		Scanner sc = new Scanner(System.in);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");


		String sql;
		int result;

		try {

			/** 図書館の登録 */
			String libraryID;
			String libraryName;
			System.out.println("図書館IDを入力してください。");
			System.out.print("⇒");
			libraryID = sc.next();
			System.out.println("図書館名を入力してください。");
			System.out.print("⇒");
			libraryName = sc.next();

			// INSERT文の実行
			sql = "INSERT INTO " + Kadai4_const.TABLE_LIBRARY + " VALUES('" + libraryID + "', '" + libraryName + "');";
			System.out.println("実行SQL文 -> " + sql);

			// INSERT結果の表示
			result = insertDB(con, stm, sql);
			System.out.println("更新件数：" + result + "件");

			System.out.println("");

			/** 本の登録 */
			String bookID;
			String bookGenre;
			String bookTitle;
			int bookPrice;
			String bookAuthor;
			String bookPublisher;
			String nowDate = sdf.format(date);
			System.out.println("本IDを入力してください。");
			System.out.print("⇒");
			bookID = sc.next();
			System.out.println("ジャンルを入力してください。");
			System.out.print("⇒");
			bookGenre = sc.next();
			System.out.println("タイトルを入力してください。");
			System.out.print("⇒");
			bookTitle = sc.next();
			System.out.println("価格を入力してください。");
			System.out.print("⇒");
			bookPrice = sc.nextInt();
			System.out.println("著者を入力してください。");
			System.out.print("⇒");
			bookAuthor = sc.next();
			System.out.println("出版社を入力してください。");
			System.out.print("⇒");
			bookPublisher = sc.next();

			// INSERT文の実行
			sql = "INSERT INTO " + Kadai4_const.TABLE_BOOK + " VALUES('" + bookID + "', '" + bookGenre + "', '"
			+ bookTitle + "', " + bookPrice + ", '" + bookAuthor + "', '" + bookPublisher
			+ "', '" + nowDate + "', '" + nowDate+ "');";
			System.out.println("実行SQL文 -> " + sql);

			// INSERT結果の表示
			result = insertDB(con, stm, sql);
			System.out.println("更新件数：" + result + "件");

			System.out.println("");

			/** 紐づきの登録 */
			String id;
			String lID;
			String bID;
			System.out.println("IDを入力してください。");
			System.out.print("⇒");
			id = sc.next();
			System.out.println("図書館IDを入力してください。");
			System.out.print("⇒");
			lID = sc.next();
			System.out.println("本IDを入力してください。");
			System.out.print("⇒");
			bID = sc.next();

			// INSERT文の実行
			sql = "INSERT INTO " + Kadai4_const.TABLE_LIBRARY_BOOK + " VALUES('"+ id + "', '"  + lID + "', '" + bID + "');";
			System.out.println("実行SQL文 -> " + sql);

			// INSERT結果の表示
			result = insertDB(con, stm, sql);
			System.out.println("更新件数：" + result + "件");

		} catch (Exception e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.toString());
			return false;

		} finally {
			sc.close();
		}
		System.out.println();

		return true;
	}

}
