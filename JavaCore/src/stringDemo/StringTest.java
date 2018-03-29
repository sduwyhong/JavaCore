package stringDemo;
import java.util.ArrayList;


public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = new String("a");//堆中创建新对象，指向常量
		String s2 = "a";
		String s3 = "";//指向栈中的常量
		String s4 = s2+"";//含变量的运算会创建新对象
		sameObject(s1,s4);
		sameValue(s1,s4);
		
		ArrayList list = new ArrayList();
		s1 = s2+s3;
		s4 = s2+s3;
		list.add((Object)s1);
		System.out.println(list.contains((Object)s4));
	}

	private static void sameValue(String s1, String s2) {
		// TODO Auto-generated method stub
		System.out.println(s1.equals(s2));
	}

	private static void sameObject(String s1, String s2) {
		// TODO Auto-generated method stub
		System.out.println(s1==s2);
	}

}
