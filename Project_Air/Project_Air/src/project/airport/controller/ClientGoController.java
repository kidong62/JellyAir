package project.airport.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import project.airport.dto.BoardingDTO;

//예매 후 출발하는 class (Thread 사용)
public class ClientGoController extends Thread {

	Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;

	public ClientGoController(Socket s) {

		Date date = new Date();

		try {
			this.s = s;

			dos = new DataOutputStream(s.getOutputStream());

			// 서버 접속 후 티켓 예매하기

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		Calendar cal = new GregorianCalendar(Locale.KOREA);
		DBClientController dbCC = new DBClientController();
		int num = 0;

		while (true) {

			try {

				System.out.println(BoardingDTO.getInstance().getbDestination() + "로 출발합니다 ^^");
				
				sleep(2000);
				System.out.println("하루가 지났습니다...");
				cal.add(Calendar.DAY_OF_YEAR, 1);

				sleep(2000);
				System.out.println("이틀이 지났습니다...");
				cal.add(Calendar.DAY_OF_YEAR, 1);

				sleep(2000);
				System.out.println("삼일이 지났습니다! 돌아가실 시간입니다!");
				cal.add(Calendar.DAY_OF_YEAR, 1);


				// 3일 지난 후의 해당 나라의 리스트 보여주기
				HashSet<Integer> numSet = dbCC.selectAllCountry(cal, BoardingDTO.getInstance().getbDestination());
				// 선택하기
				System.out.print("원하시는 번호를 입력하세요 : ");
				num = new Scanner(System.in).nextInt();

				if (!numSet.contains(num)) {
					System.out.println("리스트 안의 번호를 입력하세요.");
					continue;
				}

				System.out.println("즐거운 여행 되셨길 바라며, 안녕히가세요~");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
