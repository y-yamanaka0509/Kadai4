

public class Kadai4_const {


	/**
	 * 接続文字列C
	 *
	 */
	/** My SQLのurl */
	public static final String SQL_URL = "jdbc:mysql://localhost/test";

	/** My SQLのuser */
	public static final String SQL_USER = "root";

	/** My SQLのpassword */
	public static final String SQL_PASS = "rsp3swte";

	/**
	 * 本テーブル
	 *
	 */
	/** テーブル名 */
	public static final String TABLE_BOOK = "book";

	/** 本．ID */
	public static final String BOOK_COLUMN_ID = "id";

	/** 本．ジャンル */
	public static final String BOOK_COLUMN_GENRE = "genre";

	/** 本．タイトル */
	public static final String BOOK_COLUMN_TITLE = "title";

	/** 本．価格 */
	public static final String BOOK_COLUMN_PRICE = "price";

	/** 本．著者 */
	public static final String BOOK_COLUMN_AUTHOR = "author";

	/** 本．出版社 */
	public static final String BOOK_COLUMN_PUBLISHER = "publisher";

	/** 本．登録日 */
	public static final String BOOK_COLUMN_ENRTY_DATE = "entry_date";

	/** 本．更新日 */
	public static final String BOOK_COLUMN_UPDATE_DATE = "update_date";

	/** テーブル名 */
	public static final String TABLE_NAME_BOOK = "本";

	/** 本．ID */
	public static final String BOOK_COLUMN_NAME_ID = "ID";

	/** 本．ジャンル */
	public static final String BOOK_COLUMN_NAME_GENRE = "ジャンル";

	/** 本．タイトル */
	public static final String BOOK_COLUMN_NAME_TITLE = "タイトル";

	/** 本．価格 */
	public static final String BOOK_COLUMN_NAME_PRICE = "価格";

	/** 本．著者 */
	public static final String BOOK_COLUMN_NAME_AUTHOR = "著者";

	/** 本．出版社 */
	public static final String BOOK_COLUMN_NAME_PUBLISHER = "出版社";

	/** 本．登録日 */
	public static final String BOOK_COLUMN_NAME_ENRTY_DATE = "登録日";

	/** 本．更新日 */
	public static final String BOOK_COLUMN_NAME_UPDATE_DATE = "更新日";

	/**
	 * 図書館テーブル
	 *
	 */
	/** テーブル名 */
	public static final String TABLE_LIBRARY = "library";

	/** 図書館．ID */
	public static final String LIBRARY_COLUMN_LIBRARY_ID = "library_id";

	/** 図書館．図書館名 */
	public static final String LIBRARY_COLUMN_LIBRARY_NAME = "library_name";

	/** テーブル名 */
	public static final String TABLE_NAME_LIBRARY = "図書館";

	/** 図書館．ID */
	public static final String LIBRARY_COLUMN_NAME_LIBRARY_ID = "ID";

	/** 図書館．図書館名 */
	public static final String LIBRARY_COLUMN_NAME_LIBRARY_NAME = "図書館";

	/**
	 * 図書館-本テーブル
	 *
	 */
	/** テーブル名 */
	public static final String TABLE_LIBRARY_BOOK = "library_book";

	/** 図書館-本．ID */
	public static final String LIBRARY_BOOK_COLUMN_ID = "id";

	/** 図書館-本．図書館ID */
	public static final String LIBRARY_BOOK_COLUMN_LIBRARY_ID = "ilibrary_id";

	/** 図書館-本．本ID */
	public static final String LIBRARY_BOOK_COLUMN_BOOK_ID = "book_id";

	/** テーブル名 */
	public static final String TABLE_NAME_LIBRARY_BOOK = "library_book";

	/** 図書館-本．ID */
	public static final String LIBRARY_BOOK_COLUMN_NAME_ID = "ID";

	/** 図書館-本．図書館ID */
	public static final String LIBRARY_BOOK_COLUMN_NAME_LIBRARY_ID = "図書館ID";

	/** 図書館-本．本ID */
	public static final String LIBRARY_BOOK_COLUMN_NAME_BOOK_ID = "本ID";

}
