package chapter1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient_V1 {

	public static void main(String[] args) {
		
		try {
			//루프백 주소(내 컴퓨터를 지칭함)
			String serverIp="127.0.0.1";
			
			System.out.println("서버에 연결중입니다. 서버 IP="+serverIp);
			
			Socket socket = new Socket(serverIp,7777);
			
			InputStream in =socket.getInputStream();
			DataInputStream dis=new DataInputStream(in);
			
			System.out.println("서버로부터 받은 메세지: "+dis.readUTF());
			System.out.println("연결을 종료합니다.");
			
			dis.close();
			socket.close();
			System.out.println("연결이 종료되었습니다.");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
