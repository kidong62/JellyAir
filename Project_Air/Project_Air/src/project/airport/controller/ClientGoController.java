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

//���� �� ����ϴ� class (Thread ���)
public class ClientGoController extends Thread {

	Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;

	public ClientGoController(Socket s) {

		Date date = new Date();

		try {
			this.s = s;

			dos = new DataOutputStream(s.getOutputStream());

			// ���� ���� �� Ƽ�� �����ϱ�

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

				System.out.println(BoardingDTO.getInstance().getbDestination() + "�� ����մϴ� ^^");
				
				sleep(2000);
				System.out.println("�Ϸ簡 �������ϴ�...");
				cal.add(Calendar.DAY_OF_YEAR, 1);

				sleep(2000);
				System.out.println("��Ʋ�� �������ϴ�...");
				cal.add(Calendar.DAY_OF_YEAR, 1);

				sleep(2000);
				System.out.println("������ �������ϴ�! ���ư��� �ð��Դϴ�!");
				cal.add(Calendar.DAY_OF_YEAR, 1);


				// 3�� ���� ���� �ش� ������ ����Ʈ �����ֱ�
				HashSet<Integer> numSet = dbCC.selectAllCountry(cal, BoardingDTO.getInstance().getbDestination());
				// �����ϱ�
				System.out.print("���Ͻô� ��ȣ�� �Է��ϼ��� : ");
				num = new Scanner(System.in).nextInt();

				if (!numSet.contains(num)) {
					System.out.println("����Ʈ ���� ��ȣ�� �Է��ϼ���.");
					continue;
				}

				System.out.println("��ſ� ���� �Ǽ̱� �ٶ��, �ȳ���������~");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
