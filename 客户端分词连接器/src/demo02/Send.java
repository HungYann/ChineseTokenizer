package demo02;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {

	private BufferedReader console;

	private DataOutputStream dos;

	private boolean isRunning = true;

	public Send() {
		// 初始化控制台输入
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	public Send(Socket client) {
		this();
		try {
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			isRunning = false;
			CloseUtil.CloseAll(dos, console);

		}
	}

	// 1.从控制台接收数据,有界面的话可以替代
	public String getMsgFromConsole() {
		try {
			return console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}
		return "";

	}

	/**
	 * 1从控制台接收数据 2发送数据
	 */
	public void send() {
		String msg = getMsgFromConsole();
		if (null != msg && !msg.equals("")) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				isRunning = false;
				CloseUtil.CloseAll(dos, console);
			}

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 线程体
		while (isRunning) {
			send();
		}
	}

}
