package NetDemo;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPDemo {

	public static void main(String[] args){
		final Net net = new Net();
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					net.clientInit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				try {
					net.serverInit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}

}
class Net{
	public void clientInit() throws Exception {
		Socket socket = new Socket("127.0.0.1", 57053);
		String data = "来自客户端的数据";
		socket.getOutputStream().write(data.getBytes());
		socket.close();
	}
	public void serverInit() throws Exception{
		ServerSocket serverSocket = new ServerSocket(57053);
		//阻塞
		Socket socket = serverSocket.accept();
		String ip =socket.getInetAddress().getHostAddress();
		int port = socket.getPort();
		System.out.println("接受了来自"+ip+":"+port+"的连接");
		InputStream is = socket.getInputStream();
		byte[] buf = new byte[1024];
		is.read(buf);
		System.out.println(new String(buf));
		is.close();
		//先关客户端
		socket.close();
		serverSocket.close();
	}
}