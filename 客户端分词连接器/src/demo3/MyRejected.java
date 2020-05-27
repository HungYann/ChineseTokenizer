package demo02;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler {

	

    @Override

    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

    	MyChannel task = (MyChannel)r;
    	String error = "当前操作繁忙，用户请等待!";
    	task.send(error);
     
    }
}