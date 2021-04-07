package chapter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer_V2 {

	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		
		try {
			serverSocket=new ServerSocket(7777);
			System.out.println(getTime()+"서버가 준비되었습니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			
			try {
				System.out.println(getTime()+"연결요청을 기다립니다.");
				Socket socket=serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"로부터 연결요청이 들어왔습니다.");
				
				//클라이언트의 port
				System.out.println("getPort()="+socket.getPort());
				//서버의 port
				System.out.println("getLocalPort()="+socket.getLocalPort());
				
				OutputStream out=socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message1 from Server");
				System.out.println(getTime()+"데이터를 전송했습니다.");
				dos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static String getTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("[hh:mm:ss]");
		return sdf.format(new Date());
	}
}
