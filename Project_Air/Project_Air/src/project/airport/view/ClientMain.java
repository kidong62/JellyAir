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
			System.out.println("������ ����Ǿ����ϴ�.");

			// Main Thread ����
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
		System.out.println("================ Jelly ���׿� ���Ű��� ȯ���մϴ� ===================");
		while (true) {
			System.out.print("���̵� �Է��ϼ��� : ");
			String id = new Scanner(System.in).nextLine();
			System.out.print("�н����带 �Է��ϼ��� : ");
			String pass = new Scanner(System.in).nextLine();

			DBAdminController dc = new DBAdminController();

			// ���̵�, �н����带 ��ȸ�� ��,
			dc.selectByPnum(id, pass);
			// ��ݻ�����ȸ
			dbac.selectLoginProhibit(id);

			if (id.equals("M23325") && pass.equals("oracle")) {
				ClientDTO.getInstance().setpNum(id);
				adminMenu();
				break;
			} else if (ClientDTO.getInstance().getpYesOrNot()!= null && ClientDTO.getInstance().getpYesOrNot().equals("N")) {
				System.out.println("�� ����Ʈ ȸ���� �α����� �Ұ��մϴ�. ����ڿ��� �����ٶ��ϴ�.");
			} else if (id.equals(ClientDTO.getInstance().getpNum()) && pass.equals(ClientDTO.getInstance().getPass())) {
				ClientDTO.getInstance().setpNum(id);
				clientMenu();
				break;
			} else {
				System.out.println("���̵�/ �н����尡 ��ġ���� �ʽ��ϴ�. �ٽ� �Է����ּ���.");
				continue;
			}
		}
	}

	// Ŭ���̾�Ʈ �޴�
	public static void clientMenu() {

		int menu = 0;
		ClientBookController ct = new ClientBookController();
		DBClientController dbClient = new DBClientController();
		ClientGoController cg = new ClientGoController(s);

		while (true) {
			int nameLength = ClientDTO.getInstance().getpName().length() + "�� ȯ���մϴ� ^0^".length();
			// ���ڿ� ���� ���ϱ�
			String repeated = new String(new char[nameLength]).replace("\0", "=");
			// ���ڿ� �ݺ��ϱ�
			System.out.println(
					"\n================ " + ClientDTO.getInstance().getpName() + "�� ȯ���մϴ� ^0^ ===================");
			System.out.println("1. �����ϱ�");
			System.out.println("2. ���� ���� Ȯ��");
			System.out.println("3. ����ϱ�");
			System.out.println("4. ����");
			System.out.println("5. ���� ���");
			System.out.println("6. 1:1 ��ڿ� �ǽð� ä��");
			System.out.println("=================" + repeated + "=======================");
			System.out.print("���ϴ� �޴��� �Է����ּ��� : ");
			menu = new Scanner(System.in).nextInt();

			switch (menu) {
			case 1:
				ct.bookAirport(s);
				break;
			case 2:
				// ȸ�� ���� ������ ���ٸ�, �� �� ����.
				if (BoardingDTO.getInstance().getpNum() != null) {
					dbClient.printBoardTable(ClientDTO.getInstance().getpNum());
					break;
				} else {
					System.out.println("���� �� Ȯ�� �����մϴ�.");
					continue;
				}
			case 3:
				if (BoardingDTO.getInstance().getpNum() != null) {
					cg.start();
					break;
				} else {
					System.out.println("���� �� ��� �����մϴ�.");
					continue;
				}
			case 4:
				System.out.println("�̿����ּż� �����մϴ�.^^ �ȳ���������.");
				System.exit(0);
				break;
			case 5:
				if (BoardingDTO.getInstance().getpNum() != null) {
					dbClient.deleteBook();
					break;
				} else {
					System.out.println("���� �� ��� �����մϴ�.");
					continue;
				}

			default:
				System.out.println("1~6�� �Է����ּ���");
				continue;
			}
			continue;
		}
	}

	// ��� �޴�
	public static void adminMenu() {

		int menu = 0;
		DBAdminController dbAdmin = new DBAdminController();
		HashSet<String> pNumSet = new HashSet<String>();

		while (true) {
			System.out.println("================Admin �޴�===================");
			System.out.println("1. ��ü �� ��ȸ");
			System.out.println("2. ȸ�� ����");
			System.out.println("3. ����");
			System.out.println("4. �ǽð� ä��");
			System.out.println("==========================================");
			System.out.print("���ϴ� �޴��� �Է����ּ��� : ");
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
				System.out.println("�����մϴ�.");
				System.exit(0);
			default:
				System.out.println("1~3�� �Է����ּ���");
				continue;
			}
			continue;
		}
	}
}
