package chapter2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TCPMultichatServer {
	//key 사용해서 DataOutputStream을 찾을수 있따.
	public static Map<String,DataOutputStream> clients;
	static	{
		//ConcurrentHashMap : 쓰레드에 안전한 해시맵
		//쓰레드에 안전하다->한 쓰레드가 이 맵을 사용하고 있을 때
		//다른 쓰레드들은 이 맵을 사용할 수 없게끔 만드는 것
		//초기화 블록으로 설정(클래스가 로드될때 초기화 블록이 딱한번 실행되어야하니까..static을 붙인다. 안그러면 인스턴스 생성할때마다 실행되므로 static붙임.)
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
			System.out.println("서버가 시작되었습니다.");
			
			while (true) {
				socket=serverSocket.accept();
				System.out.println("["+socket.getInetAddress()+" : "+socket.getPort()+"] 에서 접속하였습니다.");
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
