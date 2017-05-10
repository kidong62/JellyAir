package project.airport.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

	public synchronized void connectServer(){
		String ip = "127.0.0.1";
		ServerSocket ss = null;
		Socket s = null;

		try {
			ss = new ServerSocket(7777);
			s = ss.accept();

			System.out.println(s.getInetAddress() + " : " + s.getPort() + "님이 접속하였습니다.");

			ServerReceiver thread = new ServerReceiver(s);
			thread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class ServerReceiver extends Thread {
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;

	public ServerReceiver(Socket s) {
		this.s = s;
		try {
			dis = new DataInputStream(s.getInputStream());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){

		try {
			System.out.println(dis.readInt() + "님이 접속하셨습니다.");
			System.out.println(dis.readUTF());
		} catch (Exception e) {
		}
	}

}
