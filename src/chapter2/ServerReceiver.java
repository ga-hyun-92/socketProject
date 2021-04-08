package chapter2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Thread {
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	
	public ServerReceiver(Socket socket) {
		this.socket=socket;
		
		try {
			in=new DataInputStream(socket.getInputStream());
			out=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		String name="";
		
		try {
			name=in.readUTF();
			
			TCPMultichatServer.sendToAll("#"+name+"님이 들어오셨습니다.");
			
			TCPMultichatServer.clients.put(name, out);
			System.out.println("현재 채팅 서버 접속자 수는"+TCPMultichatServer.clients.size()+"명 입니다.");
			
			while (in !=null) {
				TCPMultichatServer.sendToAll(in.readUTF());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			TCPMultichatServer.sendToAll("#"+name+"님이 나가셨습니다.");
			
			TCPMultichatServer.clients.remove(name);
			System.out.println("현재 채팅 서버 접속자 수는"+TCPMultichatServer.clients.size()+"명 입니다.");
		}//end try
	}//end run
}














