package ioDemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ChangeDirectoriesNameDemo {
	
	public static void findNotes(String src,String dest){
		File root = new File(src);
		String realPath = dest;
		//将此目录下文件夹中的.txt文件和.doc文件放在同名文件夹下复制到C:\Users\Administrator\Desktop\常用包\javaee笔记\javaee（1）中
		File[] files = root.listFiles();
		for(File file:files){
			//获得当前目录的笔记文件集合
			List<File> notes = getNoteFiles(file,new ArrayList<File>());
			if(notes!=null&&notes.size()>0){
				//存在笔记文件，则在javaee（1）中创建文件夹并写入笔记文件
				File newDir = new File(realPath,file.getName().substring(3));
				if(!newDir.exists()){
					newDir.mkdirs();
				}
				for(File note:notes){
					try {
						FileUtils.copyFileToDirectory(note, newDir);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else{
				System.out.println(file+"中不存在笔记文件！");
			}
		}
	}

	private static List<File> getNoteFiles(File file,List<File> notes) {
		//若是目录
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f:files){
				getNoteFiles(f,notes);
			}
		}else if(!(FilenameUtils.getExtension(file.getName()).equals("txt")||
				FilenameUtils.getExtension(file.getName()).equals("doc")||
				FilenameUtils.getExtension(file.getName()).equals("xls")||
				FilenameUtils.getExtension(file.getName()).equals("png")||
				FilenameUtils.getExtension(file.getName()).equals("docx")||
				FilenameUtils.getExtension(file.getName()).equals("ppt"))){
			//若文件既不是目标文件类型也不是目录，则返回,注意FilenameUtils.getExtension返回的字符串不带点号
//			String extension = FilenameUtils.getExtension(file.getName());
			return notes;
		}else{
			//是目标文件，则装入notes中
			notes.add(file);
		}
		return notes;
	}
	
	public static void main(String[] args){
		String src = "E:/j2ee(3)/【03】JavaEE高级核心";
		String dest = "C:/Users/Administrator/Desktop/常用包/javaee笔记/javaee（3）";
		findNotes(src, dest);
	}
}
