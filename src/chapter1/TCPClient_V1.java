package chapter1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient_V1 {

	public static void main(String[] args) {
		
		try {
			//������ �ּ�(�� ��ǻ�͸� ��Ī��)
			String serverIp="127.0.0.1";
			
			System.out.println("������ �������Դϴ�. ���� IP="+serverIp);
			
			Socket socket = new Socket(serverIp,7777);
			
			InputStream in =socket.getInputStream();
			DataInputStream dis=new DataInputStream(in);
			
			System.out.println("�����κ��� ���� �޼���: "+dis.readUTF());
			System.out.println("������ �����մϴ�.");
			
			dis.close();
			socket.close();
			System.out.println("������ ����Ǿ����ϴ�.");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
