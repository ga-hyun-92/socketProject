package chapter2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TCPMultichatServer {
	//key ����ؼ� DataOutputStream�� ã���� �ֵ�.
	public static Map<String,DataOutputStream> clients;
	static	{
		//ConcurrentHashMap : �����忡 ������ �ؽø�
		//�����忡 �����ϴ�->�� �����尡 �� ���� ����ϰ� ���� ��
		//�ٸ� ��������� �� ���� ����� �� ���Բ� ����� ��
		//�ʱ�ȭ ������� ����(Ŭ������ �ε�ɶ� �ʱ�ȭ ����� ���ѹ� ����Ǿ���ϴϱ�..static�� ���δ�. �ȱ׷��� �ν��Ͻ� �����Ҷ����� ����ǹǷ� static����.)
		clients =new ConcurrentHashMap<>();
	}
	
//	public TCPMultichatServer() {
//		clients =new ConcurrentHashMap<>();
//	}
	
	public void start() {
		ServerSocket serverSocket=null;
		Socket socket=null;
		
		try {
			serverSocket =new ServerSocket(7777);
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while (true) {
				socket=serverSocket.accept();
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] ���� �����Ͽ����ϴ�.");
				ServerReceiver thread=new ServerReceiver(socket);
				thread.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendToAll(String msg) {
		Iterator<String> it=clients.keySet().iterator();
		
		while (it.hasNext()) {
			try {
				String key=it.next();
				DataOutputStream out=(DataOutputStream)clients.get(it.next());
				out.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}//end try
		}//end while
	}//end sendToAll
	
	public static void main(String[] args) {
		new TCPMultichatServer().start();

	}

}
