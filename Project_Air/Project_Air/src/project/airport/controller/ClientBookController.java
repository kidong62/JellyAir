package project.airport.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import project.airport.dto.BoardingDTO;
import project.airport.dto.ClientDTO;

// 예매 클래스
public class ClientBookController {

	static Scanner sc = new Scanner(System.in);
	static String id;
	static String pass;

	Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;
	static Date date;
	// 회원용 DB controller
	static DBClientController dbsc = new DBClientController();
	// 운영자용 DB controller
	static DBAdminController dbac = new DBAdminController();

	// 예매 관련 메소드
	public void bookAirport(Socket s) {
		int cnt = 0;
		// 예매할 번호를 입력받을 num
		int num = 0;

		while (true) {
			try {
				dos = new DataOutputStream(s.getOutputStream());

				// 오늘 날짜를 구한다. (3월 7일 기준)
				Calendar cal = new GregorianCalendar(Locale.KOREA);
				// 오늘 날짜를 구한 뒤,
				cal.setTime(new Date());
				// 4일을 더한다. (3월 3일 -> 3월 7일 테스트용. 나중에 시연할때는 없애버리면 됨.)
				cal.add(Calendar.DAY_OF_YEAR, 2);

				HashSet<Integer> numSet = dbsc.selectAllCountry(cal);
				// 모든 스케줄 리스트의 번호를 numSet에 담는다.


				System.out.print("원하시는 번호를 입력하세요 : ");
				num = new Scanner(System.in).nextInt();

				if (!numSet.contains(num) || num == 0) {
					System.out.println("리스트 안의 번호를 입력하세요.");
					continue;
				}

				// 리스트 번호를 입력하여 해당 스케줄의 정보를 가져온다
				dbsc.selectCountryFromCNUM(num);

				// 현재 날짜 이후의 DB list를 불러온다.
				System.out.println("\n================= 좌석도 =============================");
				ArrayList<String> sList = dbsc.selectAllSeat(num);
				System.out.println("==================================================");

				System.out.println("원하는 좌석을 고르세요.");
				String seat = new Scanner(System.in).nextLine();

				if (!sList.contains(seat)) {
					System.out.println("리스트 안의 좌석에서 고르세요.");
					continue;
				}

				// 좌석 정보를 update한다.
				dbsc.updateSeat(seat, num);

				// 현재 회원정보를 조회한다.
				System.out.println("========= 현재 예매 회원 정보 =========");
				dbac.selecyByPnum(ClientDTO.getInstance().getpNum());

				// 예매한 회원 정보를 Board Table에 넣기. -> 예매하기
				dbsc.insertBoardTable();
				System.out.println("======== 현재 예매 회원의 탑승권 정보 ========");
				dbsc.printBoardTable(ClientDTO.getInstance().getpNum());
				// dos.writeInt(num);

				
				System.out.println("============ 예매종료 ============");
				// dos.writeUTF("접속종료");
				
				BoardingDTO.getInstance().setpNum(ClientDTO.getInstance().getpNum());
				// BoardingDTO에 여권번호 정보를 넣는다.
				
				dos.writeUTF(ClientDTO.getInstance().getpName() + "님이 " + BoardingDTO.getInstance().getbStartSite() +"에서 예매 완료하였습니다.");

				break;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

}
