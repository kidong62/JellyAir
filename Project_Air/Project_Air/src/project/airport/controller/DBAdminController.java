package project.airport.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

import project.airport.dbConn.Util.ConnectionSingletonHelper;
import project.airport.dto.BoardingDTO;
import project.airport.dto.ClientDTO;

public class DBAdminController {

	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs = null;

	// 모든 회원 조회
	public HashSet<String> selectAllClient() {
		HashSet<String> set = new HashSet<String>();
		try {
			String sql = "select * from client";
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			System.out.println("\n여권번호 \t 이름 \t 주소 \t\t 나이 \t 국적 \t 결격사유 \t 성별 \t 직업 \t 마일리지");
			while (rs.next()) {
				System.out.println();
				String pNum = rs.getString("PNUM");
				String pName = rs.getString("PNAME");
				String pAddr = rs.getString("PADDR");
				String pAge = rs.getString("PAGE");
				String pCountry = rs.getString("PCOUNTRY");
				String pYesOrNot = rs.getString("PYESORNOT");
				String pSex = rs.getString("PSEX");
				String pJob = rs.getString("PJOB");
				int pMileage = rs.getInt("PMILEAGE");
				String pass = rs.getString("PASSWORD");

				ClientDTO.getInstance().setpNum(pNum);
				ClientDTO.getInstance().setpName(pName);
				ClientDTO.getInstance().setpAddr(pAddr);
				ClientDTO.getInstance().setpAge(pAge);
				ClientDTO.getInstance().setpCountry(pCountry);
				ClientDTO.getInstance().setpYesOrNot(pYesOrNot);
				ClientDTO.getInstance().setpSex(pSex);
				ClientDTO.getInstance().setpJob(pJob);
				ClientDTO.getInstance().setpMileage(pMileage);
				ClientDTO.getInstance().setPass(pass);

				set.add(pNum);
				print();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return set;
	}

	// 여권번호로 고객 조회
	public void selecyByPnum(String id) {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			String sql = "select * from client where pnum = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();

				String pNum = rs.getString("PNUM");
				String pName = rs.getString("PNAME");
				String pAddr = rs.getString("PADDR");
				String pAge = rs.getString("PAGE");
				String pCountry = rs.getString("PCOUNTRY");
				String pYesOrNot = rs.getString("PYESORNOT");
				String pSex = rs.getString("PSEX");
				String pJob = rs.getString("PJOB");
				int pMileage = rs.getInt("PMILEAGE");
				String pass = rs.getString("PASSWORD");

				ClientDTO.getInstance().setpNum(pNum);
				ClientDTO.getInstance().setpName(pName);
				ClientDTO.getInstance().setpAddr(pAddr);
				ClientDTO.getInstance().setpAge(pAge);
				ClientDTO.getInstance().setpCountry(pCountry);
				ClientDTO.getInstance().setpYesOrNot(pYesOrNot);
				ClientDTO.getInstance().setpSex(pSex);
				ClientDTO.getInstance().setpJob(pJob);
				ClientDTO.getInstance().setpMileage(pMileage);
				ClientDTO.getInstance().setPass(pass);

				print();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 로그인용 메소드
	public void selectByPnum(String id, String passNum) {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			String sql = "select * from client where pnum = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passNum);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				String pNum = rs.getString("PNUM");
				String pName = rs.getString("PNAME");
				String pAddr = rs.getString("PADDR");
				String pAge = rs.getString("PAGE");
				String pCountry = rs.getString("PCOUNTRY");
				String pSex = rs.getString("PSEX");
				String pJob = rs.getString("PJOB");
				int pMileage = rs.getInt("PMILEAGE");
				String pass = rs.getString("PASSWORD");

				ClientDTO.getInstance().setpNum(pNum);
				ClientDTO.getInstance().setpName(pName);
				ClientDTO.getInstance().setpAddr(pAddr);
				ClientDTO.getInstance().setpAge(pAge);
				ClientDTO.getInstance().setpCountry(pCountry);
				ClientDTO.getInstance().setpSex(pSex);
				ClientDTO.getInstance().setpJob(pJob);
				ClientDTO.getInstance().setpMileage(pMileage);
				ClientDTO.getInstance().setPass(pass);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 보여주는 메소드
	public static void print() {

		String pNum = ClientDTO.getInstance().getpNum();
		String pName = ClientDTO.getInstance().getpName();
		String pAddr = ClientDTO.getInstance().getpAddr();
		String pAge = ClientDTO.getInstance().getpAge();
		String pCountry = ClientDTO.getInstance().getpCountry();
		String pYesOrNot = ClientDTO.getInstance().getpYesOrNot();
		String pSex = ClientDTO.getInstance().getpSex();
		String pJob = ClientDTO.getInstance().getpJob();
		int pMileage = ClientDTO.getInstance().getpMileage();

		System.out.println("여권번호 \t 이름 \t 주소 \t\t 나이 \t 국적 \t 결격사유 \t 성별 \t 직업 \t 마일리지");
		System.out.print(pNum + "\t");
		System.out.print(pName + "\t");
		System.out.print(pAddr + "\t\t");
		System.out.print(pAge + "\t");
		System.out.print(pCountry + "\t");
		System.out.print(pYesOrNot + "\t");
		System.out.print(pSex + "\t");
		System.out.print(pJob + "\t");
		System.out.print(pMileage + "\t");
	}

	// 결격사유 회원 관리
	public void updateClient(HashSet<String> set) {

		while (true) {
			try {
				System.out.print("결격 사유에 해당하는 회원 번호를 입력하세요 : ");
				String pNum = new Scanner(System.in).nextLine();

				if (set.contains(pNum)) {
					conn = ConnectionSingletonHelper.getConnection("oracle");
					String sql = "UPDATE CLIENT SET PYESORNOT = 'N' WHERE PNUM = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pNum);

					int res = pstmt.executeUpdate();

					System.out.println("결격사유에 해당하는 회원 업데이트 여부 : " + ((res > 0) ? "true" : "false"));
					break;
				} else {
					System.out.println("리스트 안의 회원 번호를 입력해주세요.");
					continue;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 결격 사유에 해당하는 회원 차단

	public void selectLoginProhibit(String pNum) {
		String sql = "select PYESORNOT from client where pnum = ?";
		String pYesOrNot = "";
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				pYesOrNot = rs.getString("PYESORNOT");
				ClientDTO.getInstance().setpYesOrNot(pYesOrNot);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
