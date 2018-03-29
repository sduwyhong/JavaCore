package NetDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class User {
	public static void main(String[] args) throws IOException, InterruptedException {
		ChatService chatService = new ChatService();
		int port = new Random().nextInt(100) + 50000;
		final ServerSocket serverSocket = new ServerSocket(port);
		//与服务器端建立的连接，用来发送消息，quit之后才close
		Socket socket = chatService.login(port);
		
		Thread receiver = new Thread(){
			public boolean stop;
			@Override
			public void run() {
				Socket socket;
				InputStream in = null;
				try {
					//与服务器端建立的连接，用来接收消息，quit之后才close
					socket = serverSocket.accept();
					in = socket.getInputStream();
					while(!stop) {
						byte[] buf = new byte[1024];
						in.read(buf);
						System.out.println(new String(buf));
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};
		receiver.start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String content = null;
		OutputStream out = null;
		while((content = reader.readLine()) != null) {
			out = socket.getOutputStream();
			out.write((port+":"+content).getBytes());
			if(content.trim().equals("quit")) break;
		}
		
		//Closing the returned OutputStream will close the associated socket.
		out.close();
		serverSocket.close();
		System.exit(0);
	}

}

class ChatServer {

	public static void main(String[] args){
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(58888);
			ExecutorService executorService = Executors.newFixedThreadPool(10);
			while(true) {
				executorService.execute(new ChatService(serverSocket.accept()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class ChatService implements Runnable {

	public final static String SERVER_IP = "127.0.0.1";
	private final static String USER_IP = "127.0.0.1";
	private final static int SERVER_PORT = 58888;

	private static Map<Integer, Socket> online = new ConcurrentHashMap<Integer, Socket>();
	//与客户端建立的连接，用来接收客户端消息，客户端quit后才close
	private Socket socket;
	public ChatService(){}
	public ChatService(Socket socket) {
		this.socket = socket; 
	}
	public Socket login(int port) throws UnknownHostException, IOException{
		//用户接入，先发消息给服务器
		Socket _socket = null;
		while(_socket == null) {
			try {
				Thread.sleep(3000);
				System.out.println("try to connect server");
				_socket = new Socket(SERVER_IP, SERVER_PORT);
			} catch (Exception e) {
				System.out.println("connection failed, reconnect in 3s");
			}
		}
		System.out.println("connected");
		String _port = port+":上线了";
		OutputStream out = _socket.getOutputStream();
		out.write(_port.getBytes());
		return _socket;
	}
	@Override
	public void run() {
		InputStream in = null;
		OutputStream out = null;
		//引用输出socket对象
		Socket _socket = null;
		try {
			in = socket.getInputStream();
			while(!socket.isClosed()) {
				byte[] buf = new byte[1024];
				in.read(buf);
				String content = new String(buf);
				if(!content.isEmpty()){
					String[] portColonContent = content.split(":");
					int port = Integer.parseInt(portColonContent[0]);
					if(portColonContent[1].trim().equals("quit")) {
						disconnect_notice(port);
						//连接关闭，及时退出循环，否则会多循环一次（线程执行顺序问题）
						break;
					}else{
						Set<Integer> ports = online.keySet();
						if(!online.containsKey(port)) {
							if(online.size() > 0) {
								for (Integer user_port : ports) {
									_socket = online.get(user_port);
									out = _socket.getOutputStream();
									String notice = "*****"+port+"上线了*****";
									out.write(notice.getBytes());
								} 
							}
							//建立与此客户端的连接，用来向客户端发送消息，客户端quit后才close
							online.put(port, new Socket(USER_IP, port));
						}else {
							for (Integer user_port : ports) {
								_socket  = online.get(user_port);
								out = _socket.getOutputStream();
								out.write(content.getBytes());
							}
						}
					}
				}
			}
		}catch (Exception e) {
			System.out.println("出错啦:"+e.getMessage());
			e.printStackTrace();
		}
	}
	private void disconnect_notice(int port) throws UnknownHostException, IOException {
		Socket _socket = null;
		OutputStream out = null;
		online.get(port).close();
		online.remove(new Integer(port));
		if(online.size() > 0) {
			Set<Integer> ports = online.keySet();
			for (Integer user_port : ports) {
				_socket = online.get(user_port);
				out = _socket.getOutputStream();
				String notice = "*****"+port+"下线了*****";
				out.write(notice.getBytes());
			} 
		}
	}
}