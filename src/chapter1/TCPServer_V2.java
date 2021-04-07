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
			System.out.println(getTime()+"������ �غ�Ǿ����ϴ�.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			
			try {
				System.out.println(getTime()+"�����û�� ��ٸ��ϴ�.");
				Socket socket=serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"�κ��� �����û�� ���Խ��ϴ�.");
				
				//Ŭ���̾�Ʈ�� port
				System.out.println("getPort()="+socket.getPort());
				//������ port
				System.out.println("getLocalPort()="+socket.getLocalPort());
				
				OutputStream out=socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message1 from Server");
				System.out.println(getTime()+"�����͸� �����߽��ϴ�.");
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
