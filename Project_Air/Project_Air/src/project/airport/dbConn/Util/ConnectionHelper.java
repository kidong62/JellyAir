package project.airport.dbConn.Util;

import java.sql.Connection;
import java.sql.DriverManager;

// DB ���� ���� �ݺ������� �ڵ� �ذ�
// �ٸ� Ŭ�������� �Ʒ� �ڵ� ������ ���� �ʵ��� ����
// Class.forName("oracle.jdbc.OracleDriver");
// conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb","kingsmile","mysql");

// �̷������� ���
// ConnectionHelper.getConnection("mysql") or ("oracle")
// dsn ==> data source name

public class ConnectionHelper {
	// �Լ� (������ : public, static)
	public static Connection getConnection(String dsn) {

		Connection conn = null;
		try {

			if (dsn.equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");// ����̹� Ŭ���� �̸� ���
				conn = DriverManager.getConnection("jdbc:mysql://localhost:1521/sampledb", "ohhyuna", "mysql");

			} else if (dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver"); // ����̹� Ŭ���� �̸� ���
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ohhyuna", "oracle");
			}
			
			System.out.println(dsn + " ����̹� �ε� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;

		}
	}

	public static Connection getConnection(String dsn, String userid, String pwd) {

		Connection conn = null;
		try {

			if (dsn.equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");// ����̹� Ŭ���� �̸� ���
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", userid, pwd);

			} else if (dsn.equals("oracle")) {
				Class.forName("oracle.jdbc.OracleDriver"); // ����̹� Ŭ���� �̸� ���
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", userid, pwd);

			}

			System.out.println(dsn + " ����̹� �ε� �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			return conn;

		}
	}

}
