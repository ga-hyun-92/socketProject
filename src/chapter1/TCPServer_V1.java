package chapter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

//<소켓 통신을 위해 필요한것>
//-서버->서버 프로그램을 구현함
//(서버 소켓안에 inputStream, outputStream이 있음)
//-클라이언트->서버소켓을 사용해서 클라이언트와 통신할 준비
//(서버 소켓안에 inputStream, outputStream이 있음)
//
//클라이언트->클라이언트 프로그램을 구현
//클라이언트 프로그램->소켓을 생성해서 서버에 연결을 요청

//TCPServer_V1 : 서버의 역할
public class TCPServer_V1 {

	public static void main(String[] args) {
		//연결을 받아주는 역할 serverSocket
		ServerSocket serverSocket=null;
		
		try {
			//클라이언트의 응답을 받아들일 소켓
			//7777포트를 사용한 서버 소켓 생성
			serverSocket=new ServerSocket(7777);
			System.out.println(getTime()+"서버가 준비되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				System.out.println(getTime()+"연결요청을 기다립니다.");
				
				//클라이언트가 연결요청할때까지 멈춰서 기다림. 클라이언트가 7777로 들어오면 accept실행
				Socket socket=serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"로부터 연결요청이 들어왔습니다.");
				
				//출력준비
				OutputStream out= socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);//DataOutputStream보조스트림 붙여서 데이터 전송함
				
				//데이터보냄
				dos.writeUTF("[Notice] Test Message1 from Server");
				System.out.println(getTime()+"클라이언트로 데이터를 전송했습니다.");
				
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
