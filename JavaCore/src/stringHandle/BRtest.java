package stringHandle;

public class BRtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "<p>最近开始追</p><p>还不错哟</p><p>科科</p><p></p>";
		str = str.replace("</p>", "</p><br>");
		System.out.println(str);
	}

}
