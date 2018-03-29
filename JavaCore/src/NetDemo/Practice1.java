package NetDemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//客户端并发上传图片
class Client {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			final int index = i;
			new Thread(new Runnable(){

				@Override
				public void run() {
					//向服务器端上传图片
					Socket socket;
					try {
						socket = new Socket("127.0.0.1", 54545);
						BufferedInputStream bis 
						= new BufferedInputStream(new FileInputStream(new File("e:/NetPractice/"+(index+1)+".jpg")));
						BufferedOutputStream bos 
						= new BufferedOutputStream(socket.getOutputStream());
						byte[] buf = new byte[1024];
						int len = 0;
						while((len=bis.read(buf))!=-1){
							bos.write(buf, 0, len);
						}
						bos.flush();
						bos.close();
						bis.close();
						System.out.println(Thread.currentThread().getName()+"上传完成");
						socket.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}).start();
		}
	}

}
class Server {

	static BlockingQueue<Socket> sockets = new ArrayBlockingQueue<Socket>(100);

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(54545);
			initThreadPool();
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("获取连接："+socket);
				sockets.add(socket);
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

	private static void initThreadPool() {
		final ExecutorService executorService = Executors.newFixedThreadPool(10);
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true) {
					try {
						executorService.execute(new SocketTask(sockets.take()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

}

class SocketTask implements Runnable{
	private Socket socket;
	private Random random;
	public SocketTask(Socket socket) {
		this.socket = socket;
		this.random = new Random();
	}
	@Override
	public void run() {
		try {
			BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
			String date = new SimpleDateFormat("yyyyMMddHHmmss"+random.nextInt(1000)).format(new Date());
			BufferedOutputStream bos 
				= new BufferedOutputStream(new FileOutputStream(new File("e:/NetPractice/Server/"+date+".jpg")));
			byte[] buf = new byte[1024];
			int len = 0;
			while((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			System.out.println("服务器保存图片成功！");
			bos.flush();
			bos.close();
			bis.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}