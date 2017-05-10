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

	// 리스트 전부 뽑아오기 자리리스트넣기
	public HashMap selectCountryAndPutSeat() {

		HashMap<Integer, String[]> seatMap = new HashMap<Integer, String[]>();
		try {

			String sql = "select * from COUNTRY ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int cnum = 0;

			// System.out.println("No \t 출발지 \t 출발시간 \t\t 도착지 \t\t 도착시간 \t\t
			// 남은좌석");

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

	// Country의 스케줄을 뽑아오되,
	// 출발 도시와 출발 날짜에 맞춰 뽑아옵니다.
	public HashSet<Integer> selectAllCountry(Calendar cal) {

		// 해당하는 넘버값을 가져오는 Set입니다.
		HashSet<Integer> nSet = new HashSet<Integer>();

		try {

			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String strDate = fm.format(cal.getTime());
			System.out.println("현재시간 : " + strDate);

			String sql = "select * from COUNTRY where bstarttime > ? and (bstartsite = 'INCHEON' or bstartsite = 'SEOUL') order by  bstarttime ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strDate);
			rs = pstmt.executeQuery();
			int cnum = 0;
			String destination = "";

			System.out.println("No \t 출발지 \t 출발시간 \t\t\t 도착시간 \t\t\t  남은좌석 \t  도착지 \t");

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
		
		// 해당하는 넘버값을 가져오는 Set입니다.
		HashSet<Integer> nSet = new HashSet<Integer>();
		
		try {
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String strDate = fm.format(cal.getTime());
			System.out.println("현재시간 : " + strDate);
			
			String sql = "select * from COUNTRY where bstarttime > ? and (bstartsite = ?) order by bstarttime ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strDate);
			pstmt.setString(2, destination);
			rs = pstmt.executeQuery();
			int cnum = 0;
			
			System.out.println("No \t 출발지 \t 출발시간 \t\t\t 도착시간 \t\t\t  남은좌석 \t  도착지 \t");
			
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

	// 나라의 기본키로 검색
	public void selectCountryFromCNUM(int num) {

		try {
			String sql = "select * from COUNTRY where CNUM = ? ";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("No \t 출발시간 \t\t\t 도착시간  \t\t\t 남은좌석 \t 출발지 \t\t 도착지");
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

	// 탑승 예매
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

			System.out.println("\n삽입여부 : " + ((res > 0) ? "true" : "false"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// BoardingTable 조회하기
	// Test용
	public void printBoardTable(String pNum) {
		try {

			String sql = "select * from boarding where pnum = ?";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pNum);
			rs = pstmt.executeQuery();
			
			System.out.println("여권번호  좌석번호 \t 마일리지 \t 출발날짜 \t\t 도착날짜 \t 출발지 \t 도착지");
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

	// 좌석 업데이트
	public void updateCountry(int cnt, int cnum) {

		System.out.println("남은좌석수:" + CountryDTO.getInstance().getcSitCount());

		try {

			conn = ConnectionSingletonHelper.getConnection("oracle");
			String sql = "update country set CSITCOUNT = ? where CNUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, CountryDTO.getInstance().getcSitCount() - cnt);
			pstmt.setInt(2, cnum);

			int res = pstmt.executeUpdate();

			System.out.println("좌석 수정 여부 : " + ((res > 0) ? "true" : "false"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 모든 좌석을 조회한다.
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

	// 좌석 예매할때, 좌석 수정 메소드
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

			System.out.println("좌석 업데이트 여부 : " + ((res > 0) ? "true" : "false"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 예매 취소
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
			
			System.out.println("좌석 취소 여부 : " + ((res > 0) ? "true" : "false"));

			sql = "delete from boarding where pnum = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BoardingDTO.getInstance().getpNum());
			res = pstmt.executeUpdate();

			System.out.println("예매 취소 여부 : " + ((res > 0) ? "true" : "false"));
			
			System.out.println("예매 취소를 확정하시겠습니까?(Y/N)");
			String yn = new Scanner(System.in).next();
			
			if(yn.equalsIgnoreCase("Y")){
				conn.commit();
				System.out.println("예매가 취소되었습니다.");
				BoardingDTO.getInstance().setpNum(null);
			} else {
				System.out.println("예매가 그대로 진행됩니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
