package chapter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer_V4 implements Runnable{ //implements Runnable ���̸� ������ȭ ��.
	ServerSocket serverSocket;
	Thread[] threadArr;
	
	//������
	public TCPServer_V4(int num) {
		try {
			serverSocket=new ServerSocket(7777);
			System.out.println(getTime()+"������ �غ�Ǿ����ϴ�.");
			
			threadArr=new Thread[num];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(getTime()+Thread.currentThread()+"�� �����û�� ��ٸ��ϴ�.");
				
				Socket socket=serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"�κ��� �����û�� ���Խ��ϴ�.");
				
				OutputStream out=socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message1 from Server");
				System.out.println(getTime()+"������ �����߽��ϴ�.");
				
				dos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}//end try
		}//end while
	}//end run()
	
	public void start() {
		for (int i = 0; i < threadArr.length; i++) {
			threadArr[i]=new Thread(this);//this:�� �ν��Ͻ���! ������ȭ ���Ѷ�.
			threadArr[i].start();
		}
	}//end start()
	
	
	public static void main(String[] args) {
		//TCPServer_V4Ÿ���� ������� ��ü ����-�����ڴ�?
		TCPServer_V4 server=new TCPServer_V4(5);
		
		server.start();
		
	}
	
	public static String getTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("[hh:mm:ss]");
		return sdf.format(new Date());
	}

}
