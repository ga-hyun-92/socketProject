package chapter2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSender extends Thread{
	Socket socket;
	DataOutputStream out;
	String name;
	
	//ClientSender »ý¼ºÀÚ
	public ClientSender(Socket socket, String name) {
		this.socket=socket;
		
		try {
			out=new DataOutputStream(socket.getOutputStream());
			this.name=name;
		} catch (IOException e) {
			e.printStackTrace();
		}//end try
	}
	
	public void run() {
		Scanner scanner=new Scanner(System.in);
		try {
			
			if (out !=null) {
				out.writeUTF(name);
			}	
			while (out!=null) {
				out.writeUTF("["+name+"]"+scanner.nextLine());
			}//end while
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
