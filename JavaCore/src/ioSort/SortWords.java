package ioSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SortWords {
	
	public static void main(String[] args) throws IOException{
		//获取文件绝对路径
		String filePath = args[0];
		//构建字符读取流
		Reader in = new FileReader(filePath);
		//用字符读取流构建缓冲读取流
		BufferedReader reader = new BufferedReader(in);
		//用来拼接每次读取的一行字符串
		StringBuffer sb = new StringBuffer();
		String temp = "";
		//读文件
		while((temp=reader.readLine())!=null){
			sb.append(temp);
//			sb.append("\r");
		}
		//获取文本字符串
		String text = sb.toString();
		//创建字符数组来进行单词判断
		char[] chs;
		chs = text.toCharArray();
		//用来动态存储单词
		List<String> words = new ArrayList<String>();
		//单词起始索引
		int beginIndex = 0;
		//单词终止索引
		int endIndex = 0;
		for (int i = 0; i < chs.length; i++) {
			if(!String.valueOf(chs[i]).matches("[a-zA-Z]")){
				endIndex = i;
				//substring不包括endindex
				String tempStr = text.substring(beginIndex, endIndex);
				//若读取的单词不为空且list中不含该单词，则添加
				if(!(tempStr.equals("")||words.contains(tempStr))){
					words.add(tempStr);
				}
				//改变起始索引
				beginIndex = endIndex+1;
			}
		}
		//将list转化为String数组
		String[] strs = words.toArray(new String[words.size()]);
		//按字典顺序排序
		for (int i = 0; i < strs.length-1; i++) {
			for (int j = i+1; j < strs.length; j++) {
				if(strs[i].compareToIgnoreCase(strs[j])>0){
					String s = strs[i];
					strs[i] = strs[j];
					strs[j] = s;
				}
			}
		}
		//输出
		for (String string : strs) {
			System.out.println(string);
		}
	}
}
