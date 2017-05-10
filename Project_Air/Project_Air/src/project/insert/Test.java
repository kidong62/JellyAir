package project.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.airport.dbConn.Util.ConnectionHelper;
import project.airport.dbConn.Util.ConnectionSingletonHelper;

public class Test {

	static int insert;

	public static void main(String[] args) throws SQLException {
		int cnt = 0;
		PreparedStatement pstmt = null;
		String[][] bae = new String[1000][1000];
		
		//ÃÑ »ðÀÔ°¹¼ö : 260
		for (int k = 1; k <= 13; k++) {
			for (int j = 0; j < 4; j++) {
				for (int i = 1; i <= 5; i++) {

					String sql = "insert into seat values(?, ?, ?)";
					Connection conn = ConnectionSingletonHelper.getConnection("oracle");
					pstmt = conn.prepareStatement(sql);

					System.out.print(k + (char)('A' + j) + "" + i + "\t");
					bae[i][j] = (char)('A' + j) + "" + i;
					cnt++;

					pstmt.setInt(1, k);
					pstmt.setString(2, bae[i][j]);
					pstmt.setString(3, null);

					if (cnt % 20 == 0) {
						System.out.println();
					}
					
					insert += pstmt.executeUpdate();
					System.out.println("»ðÀÔ°¹¼ö : " + insert);

				}
			}

		}
	}
}
