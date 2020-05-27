package demo02;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		String echo;
		try {
			Socket client = new Socket("localhost", 8888);
			new Thread(new Send(client)).start();
			new Thread(new Receive(client)).start();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
// 测试语句
/*
 


你年龄是多大了
你是不是喜欢我
李迪安想要一辆兰博基尼
你真逗
早上好呀
今天你吃饭了吗
谢谢你啦
今天天气好热呀
你叫什么名字

*/









