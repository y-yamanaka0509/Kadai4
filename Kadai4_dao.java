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

	// UPDATE文の実行
	public int updateDB(Connection con, Statement stm, String sql) {

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

	// WHERE句の設定
	public String addWhere(String sql, String where, Object value) {

		if (sql.contains("WHERE")) {
			sql += " AND ";
		} else {
			sql += " WHERE ";
		}

		if (value instanceof Integer) {
			return sql + where.replace("?", String.valueOf(value).replace("'", "''"));
		} else {
			return sql + where.replace("?", "'" + value.toString().replace("'", "''") + "'");
		}
	}

	// SET句の設定
	public String addSet(String sql, String key, Object value) {

		if (sql.contains("SET")) {
			sql += ", ";
		} else {
			sql += " SET ";
		}

		if (value instanceof Integer) {
			return sql + key + " = " + String.valueOf(value).replace("'", "''");
		} else {
			return sql + key + " = '" + value.toString().replace("'", "''") + "'";
		}
	}

	// SELECT文の設定（課題4_3）
	public boolean setSQLSelect4_3(Connection con, Statement stm) {

		System.out.println("SELECT文を実行します。");
		System.out.println("");

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
	public boolean setSQLInsert4_4(Connection con, Statement stm) {

		System.out.println("INSERT文を実行します。");
		System.out.println("");

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
					+ bookTitle + "', " + bookPrice + ", '" + bookAuthor + "', '" + bookPublisher + "', '" + nowDate
					+ "', '" + nowDate + "');";
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
			sql = "INSERT INTO " + Kadai4_const.TABLE_LIBRARY_BOOK + " VALUES('" + id + "', '" + lID + "', '" + bID
					+ "');";
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

	// SELECT文の設定（課題4_6）
	public boolean setSQLSelect4_6(Connection con, Statement stm) {

		System.out.println("SELECT文を実行します。");
		System.out.println("");

		Scanner sc = new Scanner(System.in);

		String sql;
		String result;
		ResultSet rs = null;

		try {

			/** 図書館の検索 */
			String libraryID;
			String libraryName;
			System.out.println("図書館の検索条件を入力してください（空欄で指定しない）。");
			System.out.print("図書館ID ⇒ ");
			libraryID = sc.nextLine();
			System.out.print("図書館名 ⇒ ");
			libraryName = sc.nextLine();

			// SELECT文の実行
			sql = "SELECT * FROM " + Kadai4_const.TABLE_LIBRARY;
			if (libraryID.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID + "=?", libraryID);
			}
			if (libraryName.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME + "=?", libraryName);
			}
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

			/** 本の検索 */
			String bookID;
			String bookGenre;
			String bookTitle;
			String bookPrice;
			String bookAuthor;
			String bookPublisher;
			String entryDate;
			String updateDate;
			System.out.println("本の検索条件を入力してください（空欄で指定しない）。");
			System.out.print("本ID ⇒ ");
			bookID = sc.nextLine();
			System.out.print("ジャンル ⇒ ");
			bookGenre = sc.nextLine();
			System.out.print("タイトル ⇒ ");
			bookTitle = sc.nextLine();
			System.out.print("価格 ⇒ ");
			bookPrice = sc.nextLine();
			System.out.print("著者 ⇒ ");
			bookAuthor = sc.nextLine();
			System.out.print("出版社 ⇒ ");
			bookPublisher = sc.nextLine();
			System.out.print("登録日時 ⇒ ");
			entryDate = sc.nextLine();
			System.out.print("更新日時 ⇒ ");
			updateDate = sc.nextLine();

			// SELECT文の実行
			sql = "SELECT * FROM " + Kadai4_const.TABLE_BOOK;
			if (bookID.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_ID + "=?", bookID);
			}
			if (bookGenre.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_GENRE + "=?", bookGenre);
			}
			if (bookTitle.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_TITLE + "=?", bookTitle);
			}
			if (bookPrice.equals("") == false) {
				try {
					sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_PRICE + "=?", Integer.parseInt(bookPrice));
				} catch (Exception e) {
					sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_PRICE + "=?", bookPrice);
				}
			}
			if (bookAuthor.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_AUTHOR + "=?", bookAuthor);
			}
			if (bookPublisher.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_PUBLISHER + "=?", bookPublisher);
			}
			if (entryDate.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_ENRTY_DATE + "=?", entryDate);
			}
			if (updateDate.equals("") == false) {
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_UPDATE_DATE + "=?", updateDate);
			}
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

			/** 紐づきの検索 */
			// SELECT文の実行
			sql = "SELECT t2." + Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME + ", t3." + Kadai4_const.BOOK_COLUMN_TITLE
					+ " FROM " + Kadai4_const.TABLE_LIBRARY_BOOK + " t1 INNER JOIN " + Kadai4_const.TABLE_LIBRARY
					+ " t2 ON t1." + Kadai4_const.LIBRARY_BOOK_COLUMN_LIBRARY_ID + " = t2."
					+ Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID + " INNER JOIN " + Kadai4_const.TABLE_BOOK + " t3 ON t1."
					+ Kadai4_const.LIBRARY_BOOK_COLUMN_BOOK_ID + " = t3." + Kadai4_const.BOOK_COLUMN_ID;
			if (bookID.equals("") == false) {
				sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_ID + "=?", bookID);
			}
			if (bookGenre.equals("") == false) {
				sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_GENRE + "=?", bookGenre);
			}
			if (bookTitle.equals("") == false) {
				sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_TITLE + "=?", bookTitle);
			}
			if (bookPrice.equals("") == false) {
				try {
					sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_PRICE + "=?", Integer.parseInt(bookPrice));
				} catch (Exception e) {
					sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_PRICE + "=?", bookPrice);
				}
			}
			if (bookAuthor.equals("") == false) {
				sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_AUTHOR + "=?", bookAuthor);
			}
			if (bookPublisher.equals("") == false) {
				sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_PUBLISHER + "=?", bookPublisher);
			}
			if (entryDate.equals("") == false) {
				sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_ENRTY_DATE + "=?", entryDate);
			}
			if (updateDate.equals("") == false) {
				sql = addWhere(sql, "t3." + Kadai4_const.BOOK_COLUMN_UPDATE_DATE + "=?", updateDate);
			}
			sql += " ORDER BY t1." + Kadai4_const.LIBRARY_BOOK_COLUMN_BOOK_ID + ", t1."
					+ Kadai4_const.LIBRARY_BOOK_COLUMN_LIBRARY_ID + ";";
			rs = selectDB(con, stm, sql);

			// SELECT結果の表示
			String lastTitle = "";
			try {
				while (rs.next()) {
					if (rs.getString(Kadai4_const.BOOK_COLUMN_TITLE).equals(lastTitle) == false) {
						lastTitle = rs.getString(Kadai4_const.BOOK_COLUMN_TITLE);
						System.out.println(lastTitle + "に紐づく図書館");
					}
					System.out.println(rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME));
				}
			} catch (SQLException e) {
				System.out.println("処理に失敗しました。");
				System.out.println(e.toString());
				return false;
			}

		} catch (Exception e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.toString());
			return false;
		} finally {
			sc.close();
		}

		return true;

	}

	// UPDATE文の設定（課題4_7）
	public boolean setSQLUpdate4_7(Connection con, Statement stm) {

		System.out.println("UPDATE文を実行します。");
		System.out.println("");

		Scanner sc = new Scanner(System.in);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String sql;
		String result;
		int resultCount;
		ResultSet rs = null;

		String type;

		try {

			System.out.println("更新対象を入力してください。（1:図書館 / 2:本 / 3:紐づき）");
			System.out.print("⇒");
			type = sc.nextLine();

			System.out.println();

			switch (type) {

			case "1":
				/** 図書館の更新 */

				sql = "SELECT * FROM " + Kadai4_const.TABLE_LIBRARY + ";";
				rs = selectDB(con, stm, sql);

				System.out.println("図書館一覧");

				// SELECT結果の表示
				try {
					while (rs.next()) {
						result = Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_ID + "："
								+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID);
						result += ", " + Kadai4_const.LIBRARY_COLUMN_NAME_LIBRARY_NAME + "："
								+ rs.getString(Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME);

						System.out.println(result);
					}
				} catch (SQLException e) {
					System.out.println("処理に失敗しました。");
					System.out.println(e.toString());
					return false;
				}
				System.out.println();

				// 更新対象の設定
				String libraryID;
				String libraryName;
				System.out.println("変更する図書館IDを入力してください。");
				System.out.print("図書館ID ⇒ ");
				libraryID = sc.nextLine();
				System.out.println("各値を入力してください。");
				System.out.print("図書館名 ⇒ ");
				libraryName = sc.nextLine();

				// UPDATE文の実行
				sql = "UPDATE " + Kadai4_const.TABLE_LIBRARY;
				sql = addSet(sql, Kadai4_const.LIBRARY_COLUMN_LIBRARY_NAME, libraryName);
				sql = addWhere(sql, Kadai4_const.LIBRARY_COLUMN_LIBRARY_ID + "=?", libraryID);

				// UPDATE結果の表示
				resultCount = updateDB(con, stm, sql);
				System.out.println("更新件数：" + resultCount + "件");

				break;

			case "2":
				/** 本の更新 */

				sql = "SELECT * FROM " + Kadai4_const.TABLE_BOOK;
				rs = selectDB(con, stm, sql);

				System.out.println("本一覧");

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

				// 更新対象の設定
				String bookID;
				String bookGenre;
				String bookTitle;
				String bookPrice;
				String bookAuthor;
				String bookPublisher;
				System.out.println("変更する本IDを入力してください。");
				System.out.print("本ID ⇒ ");
				bookID = sc.nextLine();
				System.out.println("各値を入力してください。");
				System.out.print("ジャンル ⇒ ");
				bookGenre = sc.nextLine();
				System.out.print("タイトル ⇒ ");
				bookTitle = sc.nextLine();
				System.out.print("価格 ⇒ ");
				bookPrice = sc.nextLine();
				System.out.print("著者 ⇒ ");
				bookAuthor = sc.nextLine();
				System.out.print("出版社 ⇒ ");
				bookPublisher = sc.nextLine();

				// UPDATE文の実行
				sql = "UPDATE " + Kadai4_const.TABLE_BOOK;
				sql = addSet(sql, Kadai4_const.BOOK_COLUMN_GENRE, bookGenre);
				sql = addSet(sql, Kadai4_const.BOOK_COLUMN_TITLE, bookTitle);
				try {
					sql = addSet(sql, Kadai4_const.BOOK_COLUMN_PRICE, Integer.parseInt(bookPrice));
				} catch (Exception e) {
					sql = addSet(sql, Kadai4_const.BOOK_COLUMN_PRICE, bookPrice);
				}
				sql = addSet(sql, Kadai4_const.BOOK_COLUMN_AUTHOR, bookAuthor);
				sql = addSet(sql, Kadai4_const.BOOK_COLUMN_PUBLISHER, bookPublisher);
				sql = addSet(sql, Kadai4_const.BOOK_COLUMN_UPDATE_DATE, sdf.format(date));
				sql = addWhere(sql, Kadai4_const.BOOK_COLUMN_ID + "=?", bookID);

				// UPDATE結果の表示
				resultCount = updateDB(con, stm, sql);
				System.out.println("更新件数：" + resultCount + "件");

				break;

			case "3":
				/** 図書館_本の更新 */

				sql = "SELECT * FROM " + Kadai4_const.TABLE_LIBRARY_BOOK + ";";
				rs = selectDB(con, stm, sql);

				System.out.println("紐づき一覧");

				// SELECT結果の表示
				try {
					while (rs.next()) {
						result = Kadai4_const.LIBRARY_BOOK_COLUMN_NAME_ID + "："
								+ rs.getString(Kadai4_const.LIBRARY_BOOK_COLUMN_ID);
						result += ", " + Kadai4_const.LIBRARY_BOOK_COLUMN_NAME_LIBRARY_ID + "："
								+ rs.getString(Kadai4_const.LIBRARY_BOOK_COLUMN_LIBRARY_ID);
						result += ", " + Kadai4_const.LIBRARY_BOOK_COLUMN_NAME_BOOK_ID + "："
								+ rs.getString(Kadai4_const.LIBRARY_BOOK_COLUMN_BOOK_ID);

						System.out.println(result);
					}
				} catch (SQLException e) {
					System.out.println("処理に失敗しました。");
					System.out.println(e.toString());
					return false;
				}
				System.out.println();

				// 更新対象の設定
				String id;
				String lID;
				String bID;
				System.out.println("変更するIDを入力してください。");
				System.out.print("ID ⇒ ");
				id = sc.nextLine();
				System.out.println("各値を入力してください。");
				System.out.print("図書館ID ⇒ ");
				lID = sc.nextLine();
				System.out.print("本ID ⇒ ");
				bID = sc.nextLine();

				// UPDATE文の実行
				sql = "UPDATE " + Kadai4_const.TABLE_LIBRARY_BOOK;
				sql = addSet(sql, Kadai4_const.LIBRARY_BOOK_COLUMN_LIBRARY_ID, lID);
				sql = addSet(sql, Kadai4_const.LIBRARY_BOOK_COLUMN_BOOK_ID, bID);
				sql = addWhere(sql, Kadai4_const.LIBRARY_BOOK_COLUMN_ID + "=?", id);

				// UPDATE結果の表示
				resultCount = updateDB(con, stm, sql);
				System.out.println("更新件数：" + resultCount + "件");

				break;

			default:
				break;

			}

		} catch (Exception e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.toString());
			return false;
		} finally {
			sc.close();
		}

		return true;

	}

}
