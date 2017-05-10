package project.airport.view;

import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

import project.airport.controller.ClientBookController;
import project.airport.controller.ClientGoController;
import project.airport.controller.DBAdminController;
import project.airport.controller.DBClientController;
import project.airport.dto.BoardingDTO;
import project.airport.dto.ClientDTO;

public class ClientMain extends Thread {
	static Socket s;

	public static void main(String[] args) {

		String ip = "127.0.0.1";

		try {
			s = new Socket(ip, 7777);
			System.out.println("서버에 연결되었습니다.");

			// Main Thread 시작
			Thread cMain = new Thread(new ClientMain());
			cMain.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		login();
	}

	/*
	 * PNUM PNAME PADDR PAGE PCOUNTRY PYESORNOT PSEX PJOB PMILEAGE PASSWORD
	 */
	public static void login() {

		DBAdminController dbac = new DBAdminController();
		System.out.println("================ Jelly 공항에 오신것을 환영합니다 ===================");
		while (true) {
			System.out.print("아이디를 입력하세요 : ");
			String id = new Scanner(System.in).nextLine();
			System.out.print("패스워드를 입력하세요 : ");
			String pass = new Scanner(System.in).nextLine();

			DBAdminController dc = new DBAdminController();

			// 아이디, 패스워드를 조회한 뒤,
			dc.selectByPnum(id, pass);
			// 결격사유조회
			dbac.selectLoginProhibit(id);

			if (id.equals("M23325") && pass.equals("oracle")) {
				ClientDTO.getInstance().setpNum(id);
				adminMenu();
				break;
			} else if (ClientDTO.getInstance().getpYesOrNot()!= null && ClientDTO.getInstance().getpYesOrNot().equals("N")) {
				System.out.println("블랙 리스트 회원은 로그인이 불가합니다. 담당자에게 연락바랍니다.");
			} else if (id.equals(ClientDTO.getInstance().getpNum()) && pass.equals(ClientDTO.getInstance().getPass())) {
				ClientDTO.getInstance().setpNum(id);
				clientMenu();
				break;
			} else {
				System.out.println("아이디/ 패스워드가 일치하지 않습니다. 다시 입력해주세요.");
				continue;
			}
		}
	}

	// 클라이언트 메뉴
	public static void clientMenu() {

		int menu = 0;
		ClientBookController ct = new ClientBookController();
		DBClientController dbClient = new DBClientController();
		ClientGoController cg = new ClientGoController(s);

		while (true) {
			int nameLength = ClientDTO.getInstance().getpName().length() + "님 환영합니다 ^0^".length();
			// 문자열 길이 구하기
			String repeated = new String(new char[nameLength]).replace("\0", "=");
			// 문자열 반복하기
			System.out.println(
					"\n================ " + ClientDTO.getInstance().getpName() + "님 환영합니다 ^0^ ===================");
			System.out.println("1. 예매하기");
			System.out.println("2. 예매 내역 확인");
			System.out.println("3. 출발하기");
			System.out.println("4. 종료");
			System.out.println("5. 예매 취소");
			System.out.println("6. 1:1 운영자와 실시간 채팅");
			System.out.println("=================" + repeated + "=======================");
			System.out.print("원하는 메뉴를 입력해주세요 : ");
			menu = new Scanner(System.in).nextInt();

			switch (menu) {
			case 1:
				ct.bookAirport(s);
				break;
			case 2:
				// 회원 예매 정보가 없다면, 들어갈 수 없다.
				if (BoardingDTO.getInstance().getpNum() != null) {
					dbClient.printBoardTable(ClientDTO.getInstance().getpNum());
					break;
				} else {
					System.out.println("예매 후 확인 가능합니다.");
					continue;
				}
			case 3:
				if (BoardingDTO.getInstance().getpNum() != null) {
					cg.start();
					break;
				} else {
					System.out.println("예매 후 출발 가능합니다.");
					continue;
				}
			case 4:
				System.out.println("이용해주셔서 감사합니다.^^ 안녕히가세요.");
				System.exit(0);
				break;
			case 5:
				if (BoardingDTO.getInstance().getpNum() != null) {
					dbClient.deleteBook();
					break;
				} else {
					System.out.println("예매 후 취소 가능합니다.");
					continue;
				}

			default:
				System.out.println("1~6중 입력해주세요");
				continue;
			}
			continue;
		}
	}

	// 운영자 메뉴
	public static void adminMenu() {

		int menu = 0;
		DBAdminController dbAdmin = new DBAdminController();
		HashSet<String> pNumSet = new HashSet<String>();

		while (true) {
			System.out.println("================Admin 메뉴===================");
			System.out.println("1. 전체 고객 조회");
			System.out.println("2. 회원 관리");
			System.out.println("3. 종료");
			System.out.println("4. 실시간 채팅");
			System.out.println("==========================================");
			System.out.print("원하는 메뉴를 입력해주세요 : ");
			menu = new Scanner(System.in).nextInt();

			switch (menu) {
			case 1:
				pNumSet = dbAdmin.selectAllClient();
				break;
			case 2:
				pNumSet = dbAdmin.selectAllClient();
				dbAdmin.updateClient(pNumSet);
				break;
			case 3:
				System.out.println("종료합니다.");
				System.exit(0);
			default:
				System.out.println("1~3중 입력해주세요");
				continue;
			}
			continue;
		}
	}
}
