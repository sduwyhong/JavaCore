package NetDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


class UDPSender {

	public static void main(String[] args) throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket(44444);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String content = "";
		while((content = bufferedReader.readLine()) != null) {
			if(content.equals("quit")) break;
			byte[] buf = content.getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, 
					InetAddress.getByName("127.0.0.1"), 55555);
			datagramSocket.send(datagramPacket);
		}
		datagramSocket.close();
	}

}

class UDPReceiver {
	
	public static void main(String[] args) throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket(55555);
		while(true) {
			byte[] buf = new byte[1024];
			DatagramPacket p = new DatagramPacket(buf , buf.length);
			datagramSocket.receive(p);
			String ip = p.getAddress().getHostAddress();
			int port = p.getPort();
			String content = new String(p.getData());
			System.out.println(ip+"::"+port+"说:"+content);
		}
	}

}
