package ioDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;


public class ChangeFileNameDemo {
	@Test
	public void test(){
		File file = new File("e://Exercise");
		File[] files = file.listFiles();
		for(File f:files){
			String num = f.getName().split("\\.")[0].split("_")[1];
			if(Integer.parseInt(num)<10){
				num = "0"+num;
				String newName = f.getName().split("_")[0]+"_"+num+"."+f.getName().split("\\.")[1];
				System.out.println(newName);
				File newfile = new File("e://Exercise"+file.separator+newName);
				OutputStream os = null;
				InputStream is = null;
				try {
					newfile.createNewFile();
					os = new FileOutputStream(newfile);
					is = new FileInputStream(f);
					byte[] b = new byte[1024];
					int len = 0;
					while((len=is.read(b))!=-1){
						os.write(b, 0, len);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						os.close();
						is.close();
						f.delete();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
