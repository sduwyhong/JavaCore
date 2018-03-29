package ioDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArchiveAvi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String directory = "f:/JAVA_Thread";
		File root = new File(directory);
		// TODO Auto-generated method stub
		archive(root);
	}

	private static void archive(File root) {
		// TODO Auto-generated method stub
		if(!root.isDirectory()) {
			if(root.getName().endsWith(".avi")) {
				try {
					//FileUtils.copyFile(root, new File("e:/JAVA_Thread" + File.separator + root.getName()));
					FileInputStream fis = new FileInputStream(root);
					FileOutputStream fos = new FileOutputStream(new File("f:/JAVA_Thread" + File.separator + root.getName()));
					int len = 0;
					byte[] buf = new byte[1024];
					while((len = fis.read(buf)) != -1){
						fos.write(buf);
					}
					System.out.println(root.getName() + " has been archived");
					fis.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else{
			File[] files = root.listFiles();
			for (File file : files) {
				archive(file);
			}
		}
	}

}
