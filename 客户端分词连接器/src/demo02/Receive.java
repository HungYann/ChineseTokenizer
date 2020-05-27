package demo02;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{

	//输入流
	private DataInputStream dis;
	//线程标示
	private boolean isRunning = true;
	
	public Receive()
	{
		
	}
	
	public Receive(Socket client)
	{
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (Exception e) {
			isRunning = false;
			CloseUtil.CloseAll(dis);
		}
		
	}
	
	/**
	 * 接收数据
	 * @return
	 */
	public String receive()
	{
		
		String msg = "";
		
		try {
			msg =dis.readUTF();
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.CloseAll(dis);
		}
		return msg;
	}
	
	
	@Override
	
	public void run() {
		// TODO Auto-generated method stub
		//线程体
			while(isRunning)
			{
				//String message01 = "你好";
				
				System.out.println(receive());
			}
		
	}
	
}
