package chapter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{//쓰레드화
	Socket socket;
	DataOutputStream dos;
	String name;
	
	public Sender(Socket socket) {
		this.socket=socket;
		try {
			dos=new DataOutputStream(socket.getOutputStream());
			name="["+socket.getInetAddress()+":"+socket.getPort()+"]";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void run() {
		Scanner scanf=new Scanner(System.in);
		while (dos != null) {
			try {
				dos.writeUTF(name+scanf.nextLine());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
