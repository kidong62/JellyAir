package project.airport.dbConn.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// ConneectionHelper 클래스의 문제
// 매번 드라이버 로드. => 싱글톤으로 무분별한 객체 생성 방지
public class ConnectionSingletonHelper {

	private static Connection conn;

	private ConnectionSingletonHelper() {

	}

	public static Connection getConnection(String dsn) {
		// Connection conn = null;

		if (conn != null) {
			return conn;
		}

		try {
			if (dsn.equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");// 드라이버 클래스 이름 명시
				conn = DriverManager.getConnection("jdbc:mysql://localhost:1521/sampledb", "ohhyuna", "mysql");

			} else if (dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver"); // 드라이버 클래스 이름 명시
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.252:1521:xe", "passenger", "oracle");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project1", "oracle");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}

	public static void close() throws SQLException {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}
}
