package ioDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		File src = new File("e:/SSMOnlineShop/img/user/japan.jpg");
		//outputstream要有文件名，而且不能是目录
		File dest = new File("e:/copy.jpg");
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = is.read(buffer)) != -1) {
				os.write(buffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//		List<String> list = new ArrayList<String>();
		//		list.add("1");
		//		list.add("2");
		//		list.add("3");
		//		String[] strs = new String[list.size()];
		//		list.toArray(strs);
		//		for (int i = 0; i < strs.length; i++) {
		//			System.out.println(strs[i]);
		//		}
	}
}
