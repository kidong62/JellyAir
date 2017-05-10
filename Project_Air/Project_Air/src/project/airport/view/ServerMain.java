package project.airport.view;

import project.airport.controller.ServerThread;

public class ServerMain {

	public static void main(String[] args) {

		ServerThread st = new ServerThread();
		st.connectServer();
	}

}
