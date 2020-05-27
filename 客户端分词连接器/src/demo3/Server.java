package demo02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

	public static void main(String[] args) {

		new Server().start();

	}

	public void start() {
		final int corePoolSize = 5000;
		final int maximumPoolSize = 5001;
		final int workQueue = 5000;
		int usernumber = 1;
		try {

			// 核心线程数2,先开辟两个线程，当达到2个时，将多余的线程放入队列中
			// 队列满之后，再增加活跃线程数，直到达到4个,之后就会运行拒绝策略
			// 最大线程数4,当线程词中的线程数大于核心线程数时时间才会起作用
			// 4+3
			ServerSocket server = new ServerSocket(8888);
			ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60, TimeUnit.SECONDS,
					new ArrayBlockingQueue<>(workQueue), Executors.defaultThreadFactory(),
					new ThreadPoolExecutor.AbortPolicy());
			while (true) {
				Socket client = server.accept();
				MyChannel channel = new MyChannel(client);
				pool.execute(new Thread(channel));
				// new Thread(channel).start(); // 一条道路

				if (usernumber > corePoolSize) {
					channel.send(new UserWaiting().Run());
					// 等待机制
				}

				usernumber++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
