package demo02;

import java.io.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class testClient {
	public testClient(){
	
	String cho;
	
	try {
		
		Socket client = new Socket("localhost", 8800);
		DataOutputStream output = new DataOutputStream(client.getOutputStream());
		
		Scanner input = new Scanner(System.in);
		String he = input.next();
		
		output.writeUTF(he);
		output.flush();
		
		
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public static void main(String[] args) {
		new testClient();
	}
}
