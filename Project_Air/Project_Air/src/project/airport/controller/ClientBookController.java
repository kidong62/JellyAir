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

// ���� Ŭ����
public class ClientBookController {

	static Scanner sc = new Scanner(System.in);
	static String id;
	static String pass;

	Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;
	static Date date;
	// ȸ���� DB controller
	static DBClientController dbsc = new DBClientController();
	// ��ڿ� DB controller
	static DBAdminController dbac = new DBAdminController();

	// ���� ���� �޼ҵ�
	public void bookAirport(Socket s) {
		int cnt = 0;
		// ������ ��ȣ�� �Է¹��� num
		int num = 0;

		while (true) {
			try {
				dos = new DataOutputStream(s.getOutputStream());

				// ���� ��¥�� ���Ѵ�. (3�� 7�� ����)
				Calendar cal = new GregorianCalendar(Locale.KOREA);
				// ���� ��¥�� ���� ��,
				cal.setTime(new Date());
				// 4���� ���Ѵ�. (3�� 3�� -> 3�� 7�� �׽�Ʈ��. ���߿� �ÿ��Ҷ��� ���ֹ����� ��.)
				cal.add(Calendar.DAY_OF_YEAR, 2);

				HashSet<Integer> numSet = dbsc.selectAllCountry(cal);
				// ��� ������ ����Ʈ�� ��ȣ�� numSet�� ��´�.


				System.out.print("���Ͻô� ��ȣ�� �Է��ϼ��� : ");
				num = new Scanner(System.in).nextInt();

				if (!numSet.contains(num) || num == 0) {
					System.out.println("����Ʈ ���� ��ȣ�� �Է��ϼ���.");
					continue;
				}

				// ����Ʈ ��ȣ�� �Է��Ͽ� �ش� �������� ������ �����´�
				dbsc.selectCountryFromCNUM(num);

				// ���� ��¥ ������ DB list�� �ҷ��´�.
				System.out.println("\n================= �¼��� =============================");
				ArrayList<String> sList = dbsc.selectAllSeat(num);
				System.out.println("==================================================");

				System.out.println("���ϴ� �¼��� ������.");
				String seat = new Scanner(System.in).nextLine();

				if (!sList.contains(seat)) {
					System.out.println("����Ʈ ���� �¼����� ������.");
					continue;
				}

				// �¼� ������ update�Ѵ�.
				dbsc.updateSeat(seat, num);

				// ���� ȸ�������� ��ȸ�Ѵ�.
				System.out.println("========= ���� ���� ȸ�� ���� =========");
				dbac.selecyByPnum(ClientDTO.getInstance().getpNum());

				// ������ ȸ�� ������ Board Table�� �ֱ�. -> �����ϱ�
				dbsc.insertBoardTable();
				System.out.println("======== ���� ���� ȸ���� ž�±� ���� ========");
				dbsc.printBoardTable(ClientDTO.getInstance().getpNum());
				// dos.writeInt(num);

				
				System.out.println("============ �������� ============");
				// dos.writeUTF("��������");
				
				BoardingDTO.getInstance().setpNum(ClientDTO.getInstance().getpNum());
				// BoardingDTO�� ���ǹ�ȣ ������ �ִ´�.
				
				dos.writeUTF(ClientDTO.getInstance().getpName() + "���� " + BoardingDTO.getInstance().getbStartSite() +"���� ���� �Ϸ��Ͽ����ϴ�.");

				break;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

}
