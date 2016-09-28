import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Kadai4 {

	public static void main(String args[]) {

		Kadai4_dao dao = new Kadai4_dao();
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		// String sql = "";
		// String result;

		try {

			// 接続
			con = dao.connectDB(con);
			if (con != null) {

				System.out.println("処理を選択してください。（1:SELECT / 2:INSERT / 3:UPDATE / 4:DELETE）");
				System.out.print("⇒");

				switch (sc.next()) {

				case "1":
					// SELECT文の設定
					dao.setSQLSelect(con, stm);
					break;

				case "2":
					// INSERT文の設定
					dao.setSQLInsert(con, stm);
					break;

				case "3":
					// UPDATE文の設定
					break;

				case "4":
					// DELETE文の設定
					break;

				default:
					System.out.println("処理を終了します。");
				}

			}

		} catch (Exception e) {
			System.out.println("処理に失敗しました。");
			System.out.println(e.toString());

		} finally {
			// クローズ
			dao.closeDB(con, stm, rs);
			sc.close();
		}

	}

}
