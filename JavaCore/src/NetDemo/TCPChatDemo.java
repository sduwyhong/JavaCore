package NetDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//Socket的流只能读写字节，不能读写字符
class User1 {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(54545);
			Socket socket = null;
			while(socket == null) {
				try {
					Thread.sleep(3000);
					System.out.println("try to connect 127.0.0.1:45454");
					socket = new Socket("127.0.0.1",45454);
				} catch (Exception e) {
					System.out.println("connection failed, reconnect in 3s");
				}
			}
			System.out.println("connected");
			final PlatForm platForm = new PlatForm(socket, serverSocket);
			new Thread(new Runnable(){

				@Override
				public void run() {
					platForm.initAcceptThread();
				}
				
			}).start();
			
			new Thread(new Runnable(){
				
				@Override
				public void run() {
					platForm.initSendThread();
				}
				
			}).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class User2 {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(45454);
			Socket socket = null;
			while(socket == null) {
				try {
					Thread.sleep(3000);
					System.out.println("try to connect 127.0.0.1:54545");
					socket = new Socket("127.0.0.1",54545);
				} catch (Exception e) {
					System.out.println("connection failed, reconnect in 3s");
				}
			}
			System.out.println("connected");
			final PlatForm platForm = new PlatForm(socket, serverSocket);
			new Thread(new Runnable(){

				@Override
				public void run() {
					platForm.initAcceptThread();
				}
				
			}).start();
			
			new Thread(new Runnable(){
				
				@Override
				public void run() {
					platForm.initSendThread();
				}
				
			}).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class PlatForm{
	private Socket socket;
	private ServerSocket serverSocket;
	public PlatForm(Socket socket, ServerSocket serverSocket) {
		super();
		this.socket = socket;
		this.serverSocket = serverSocket;
	}

	public void initSendThread() {
		BufferedReader br = null;
		OutputStream os = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			os = socket.getOutputStream();
			while(true) {
				//获取到内容
				os.write(br.readLine().getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				os.close();
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	public void initAcceptThread() {
		while(true) {
			InputStream is = null;
			try {
				Socket socket = serverSocket.accept();
				String ip = socket.getInetAddress().getHostAddress();
				int port = socket.getPort();
				is = socket.getInputStream();
				byte[] buf = new byte[1024];
				int len = 0;
				while((len = is.read(buf)) != -1) {
					System.out.println(ip+"::"+port+" says "+new String(buf));
					buf = new byte[1024];
				}
			} catch (IOException e) {
				if(socket.isClosed()) {
					System.out.println("disconnected");
					System.exit(0);
				}
			} finally {
				try {
					is.close();
					socket.close();
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}