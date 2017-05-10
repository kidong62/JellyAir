package project.airport.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import project.airport.dbConn.Util.ConnectionSingletonHelper;
import project.airport.dto.BoardingDTO;
import project.airport.dto.ClientDTO;
import project.airport.dto.CountryDTO;
import project.airport.dto.SitDTO;

public class DBClientController {

	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs = null;

	// ����Ʈ ���� �̾ƿ��� �ڸ�����Ʈ�ֱ�
	public HashMap selectCountryAndPutSeat() {

		HashMap<Integer, String[]> seatMap = new HashMap<Integer, String[]>();
		try {

			String sql = "select * from COUNTRY ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnum = 0;

			// System.out.println("No \t ����� \t ��߽ð� \t\t ������ \t\t �����ð� \t\t
			// �����¼�");

			while (rs.next()) {
				cnum = rs.getInt("CNUM");
				// System.out.print(cnum + "\t");
				// System.out.print(rs.getString("BSTARTSITE") + "\t");
				// System.out.print(rs.getString("BSTARTTIME") + "\t");
				// System.out.print(rs.getString("BDESTINATION") + "\t");
				// System.out.print(rs.getString("BDEPARTTIME") + "\t\t");
				// System.out.println(rs.getString("CSITCOUNT"));
				seatMap.put(cnum, SitDTO.getInstance().getTotalClass());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seatMap;
	}

	// Country�� �������� �̾ƿ���,
	// ��� ���ÿ� ��� ��¥�� ���� �̾ƿɴϴ�.
	public HashSet<Integer> selectAllCountry(Calendar cal) {

		// �ش��ϴ� �ѹ����� �������� Set�Դϴ�.
		HashSet<Integer> nSet = new HashSet<Integer>();

		try {

			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String strDate = fm.format(cal.getTime());
			System.out.println("����ð� : " + strDate);

			String sql = "select * from COUNTRY where bstarttime > ? and (bstartsite = 'INCHEON' or bstartsite = 'SEOUL') order by  bstarttime ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strDate);
			rs = pstmt.executeQuery();
			int cnum = 0;
			String destination = "";

			System.out.println("No \t ����� \t ��߽ð� \t\t\t �����ð� \t\t\t  �����¼� \t  ������ \t");

			while (rs.next()) {
				cnum = rs.getInt("CNUM");
				destination = rs.getString("BDESTINATION").toUpperCase();
				int losAngel = "LOS ANGELS".length();

				System.out.print(cnum + "\t");
				System.out.print(rs.getString("BSTARTSITE") + "\t");
				System.out.print(rs.getString("BSTARTTIME") + "\t");
				System.out.print(rs.getString("BDEPARTTIME") + "\t");
				System.out.print(rs.getString("CSITCOUNT") + "\t");
				System.out.println(destination + ((destination.length() == losAngel) ? "\t" : "\t\t"));

				nSet.add(cnum);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nSet;
	}
	
	
	public HashSet<Integer> selectAllCountry(Calendar cal, String destination) {
		
		// �ش��ϴ� �ѹ����� �������� Set�Դϴ�.
		HashSet<Integer> nSet = new HashSet<Integer>();
		
		try {
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String strDate = fm.format(cal.getTime());
			System.out.println("����ð� : " + strDate);
			
			String sql = "select * from COUNTRY where bstarttime > ? and (bstartsite = ?) order by bstarttime ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strDate);
			pstmt.setString(2, destination);
			rs = pstmt.executeQuery();
			int cnum = 0;
			
			System.out.println("No \t ����� \t ��߽ð� \t\t\t �����ð� \t\t\t  �����¼� \t  ������ \t");
			
			while (rs.next()) {
				cnum = rs.getInt("CNUM");
				destination = rs.getString("BDESTINATION").toUpperCase();
				int losAngel = "LOS ANGELS".length();
				
				System.out.print(cnum + "\t");
				System.out.print(rs.getString("BSTARTSITE") + "\t");
				System.out.print(rs.getString("BSTARTTIME") + "\t");
				System.out.print(rs.getString("BDEPARTTIME") + "\t");
				System.out.print(rs.getString("CSITCOUNT"));
				System.out.println(destination + ((destination.length() == losAngel) ? "\t" : "\t\t"));
				
				nSet.add(cnum);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nSet;
	}

	// ������ �⺻Ű�� �˻�
	public void selectCountryFromCNUM(int num) {

		try {
			String sql = "select * from COUNTRY where CNUM = ? ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("No \t ��߽ð� \t\t\t �����ð�  \t\t\t �����¼� \t ����� \t\t ������");
			while (rs.next()) {
				int cNum = rs.getInt("CNUM");
				String startTime = rs.getString("BSTARTTIME");
				String bDeparttime = rs.getString("BDEPARTTIME");
				int cSitCount = rs.getInt("CSITCOUNT");
				String bStartsite = rs.getString("BSTARTSITE");
				String bDestination = rs.getString("BDESTINATION");

				System.out.print(cNum + "\t");
				System.out.print(startTime + "\t");
				System.out.print(bDeparttime + "\t");
				System.out.print(cSitCount + "\t");
				System.out.print(bStartsite + "\t\t");
				System.out.println(bDestination + "\t");

				CountryDTO.getInstance().setbStartSite(bStartsite);
				CountryDTO.getInstance().setbStartTime(startTime);
				CountryDTO.getInstance().setbDestination(bDestination);
				CountryDTO.getInstance().setbDepartTime(bDeparttime);
				CountryDTO.getInstance().setcSitCount(cSitCount);
				CountryDTO.getInstance().setcNum(cNum);
				

				BoardingDTO.getInstance().setbStartSite(bStartsite);
				BoardingDTO.getInstance().setbStartTime(startTime);
				
				BoardingDTO.getInstance().setbDestination(bDestination);
				BoardingDTO.getInstance().setbDepartTime(bDeparttime);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ž�� ����
	public void insertBoardTable() {
		try {

			String sql = "insert into boarding values (?, ?, ?, ?, ?, ?, ?)";
			conn = ConnectionSingletonHelper.getConnection("oracle");

			String startTime = BoardingDTO.getInstance().getbStartTime();
			String departTime = BoardingDTO.getInstance().getbDepartTime();

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ClientDTO.getInstance().getpNum());
			pstmt.setString(2, BoardingDTO.getInstance().getpSit());
			pstmt.setInt(3, ClientDTO.getInstance().getpMileage());
			pstmt.setString(4, startTime);
			pstmt.setString(5, departTime);
			pstmt.setString(6, BoardingDTO.getInstance().getbDestination());
			pstmt.setString(7, BoardingDTO.getInstance().getbStartSite());
			int res = pstmt.executeUpdate();

			System.out.println("\n���Կ��� : " + ((res > 0) ? "true" : "false"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// BoardingTable ��ȸ�ϱ�
	// Test��
	public void printBoardTable(String pNum) {
		try {

			String sql = "select * from boarding where pnum = ?";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pNum);
			rs = pstmt.executeQuery();
			
			System.out.println("���ǹ�ȣ  �¼���ȣ \t ���ϸ��� \t ��߳�¥ \t\t ������¥ \t ����� \t ������");
			while (rs.next()) {
				System.out.print(rs.getString(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\t");
				System.out.print(rs.getString(4) + "\t");
				System.out.print(rs.getString(5) + "\t");
				System.out.print(rs.getString(6) + "\t");
				System.out.println(rs.getString(7) + "\t");
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	// �¼� ������Ʈ
	public void updateCountry(int cnt, int cnum) {

		System.out.println("�����¼���:" + CountryDTO.getInstance().getcSitCount());

		try {

			conn = ConnectionSingletonHelper.getConnection("oracle");
			String sql = "update country set CSITCOUNT = ? where CNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, CountryDTO.getInstance().getcSitCount() - cnt);
			pstmt.setInt(2, cnum);

			int res = pstmt.executeUpdate();

			System.out.println("�¼� ���� ���� : " + ((res > 0) ? "true" : "false"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ��� �¼��� ��ȸ�Ѵ�.
	public ArrayList<String> selectAllSeat(int cnum) {

		ArrayList<String> list = new ArrayList<String>();
		int cnt = 0;

		try {
			String sql = "select seat from seat where cnum = ?";
			conn = ConnectionSingletonHelper.getConnection("oracle");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String seat = rs.getString("seat");
				System.out.print(seat + "\t");
				cnt++;
				if (cnt % 5 == 0) {
					System.out.println();
				}
				list.add(seat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// �¼� �����Ҷ�, �¼� ���� �޼ҵ�
	public void updateSeat(String seat, int cnum) {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			String sql = "update seat set seat = '' , pnum = ? where seat = ? and cnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ClientDTO.getInstance().getpNum());
			pstmt.setString(2, seat);
			pstmt.setInt(3, cnum);

			int res = pstmt.executeUpdate();

			BoardingDTO.getInstance().setpSit(seat);

			System.out.println("�¼� ������Ʈ ���� : " + ((res > 0) ? "true" : "false"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ���
	public void deleteBook() {
		try {
			
			conn = ConnectionSingletonHelper.getConnection("oracle");
			conn.setAutoCommit(false);
			
			String sql = "update seat set seat = ?, pnum = '' where cnum = ? and pnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BoardingDTO.getInstance().getpSit());
			pstmt.setInt(2, CountryDTO.getInstance().getcNum());
			pstmt.setString(3, ClientDTO.getInstance().getpNum());
			int res = pstmt.executeUpdate();
			
			System.out.println("�¼� ��� ���� : " + ((res > 0) ? "true" : "false"));

			sql = "delete from boarding where pnum = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BoardingDTO.getInstance().getpNum());
			res = pstmt.executeUpdate();

			System.out.println("���� ��� ���� : " + ((res > 0) ? "true" : "false"));
			
			System.out.println("���� ��Ҹ� Ȯ���Ͻðڽ��ϱ�?(Y/N)");
			String yn = new Scanner(System.in).next();
			
			if(yn.equalsIgnoreCase("Y")){
				conn.commit();
				System.out.println("���Ű� ��ҵǾ����ϴ�.");
				BoardingDTO.getInstance().setpNum(null);
			} else {
				System.out.println("���Ű� �״�� ����˴ϴ�.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
