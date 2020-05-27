package demo02;

import java.io.*;
import java.net.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class testServer {

	// private DataOutputStream output;
	public static void main(String[] args) {
		new testServer();
	}

	private int width;
	// ExecutorService executor = Executors.newFixedThreadPool(3);

	public testServer() {
		try {
			ServerSocket serversocket = new ServerSocket(8800);
			while (true) {
				Socket socket = serversocket.accept();
				Handle handle = new Handle(socket);

				Thread thr = new Thread(handle);
				thr.start();
				// executor.execute(thr);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block

			// e.printStackTrace();
		}

	}

	class Handle implements Runnable {

		Socket socket;

		private int sum;

		public Handle(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {

				DataInputStream input = new DataInputStream(socket.getInputStream());

				String msg = input.readUTF();

				int width = Integer.parseInt(msg);
				sum = width * width;
				System.out.println(sum);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
