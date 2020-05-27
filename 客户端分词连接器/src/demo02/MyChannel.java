package demo02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * 一个客户端一条道路 1.输入流 2.输出流 3.接收数据 4.发送数据
 * 
 * @author liuhongyang
 *
 */
public class MyChannel implements Runnable {

	private DataInputStream dis;
	private DataOutputStream dos;
	private boolean isRunning = true;

	public MyChannel() {
	}

	public MyChannel(Socket client) {
		try {
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			CloseUtil.CloseAll(dis, dos);
			isRunning = false;
		}

	}

	private String receive() {
		String msg = "";

		try {
			msg = dis.readUTF();
		} catch (Exception e) {
			CloseUtil.CloseAll(dis);
			isRunning = false;
		}
		return msg;
	}

	void send(String msg) {
		if (null == msg || msg.equals("")) {
			return;
		}
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			CloseUtil.CloseAll(dos);
			isRunning = false;
		}
	}

	@Override
	public void run() {
		while (isRunning) {

			String statement = receive();
			int number = statement.length();
			try {
				send(new FenCi(number, statement).main());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
