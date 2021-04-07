package chapter1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

//<���� ����� ���� �ʿ��Ѱ�>
//-����->���� ���α׷��� ������
//(���� ���Ͼȿ� inputStream, outputStream�� ����)
//-Ŭ���̾�Ʈ->���������� ����ؼ� Ŭ���̾�Ʈ�� ����� �غ�
//(���� ���Ͼȿ� inputStream, outputStream�� ����)
//
//Ŭ���̾�Ʈ->Ŭ���̾�Ʈ ���α׷��� ����
//Ŭ���̾�Ʈ ���α׷�->������ �����ؼ� ������ ������ ��û

//TCPServer_V1 : ������ ����
public class TCPServer_V1 {

	public static void main(String[] args) {
		//������ �޾��ִ� ���� serverSocket
		ServerSocket serverSocket=null;
		
		try {
			//Ŭ���̾�Ʈ�� ������ �޾Ƶ��� ����
			//7777��Ʈ�� ����� ���� ���� ����
			serverSocket=new ServerSocket(7777);
			System.out.println(getTime()+"������ �غ�Ǿ����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				System.out.println(getTime()+"�����û�� ��ٸ��ϴ�.");
				
				//Ŭ���̾�Ʈ�� �����û�Ҷ����� ���缭 ��ٸ�. Ŭ���̾�Ʈ�� 7777�� ������ accept����
				Socket socket=serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+"�κ��� �����û�� ���Խ��ϴ�.");
				
				//����غ�
				OutputStream out= socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);//DataOutputStream������Ʈ�� �ٿ��� ������ ������
				
				//�����ͺ���
				dos.writeUTF("[Notice] Test Message1 from Server");
				System.out.println(getTime()+"Ŭ���̾�Ʈ�� �����͸� �����߽��ϴ�.");
				
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
