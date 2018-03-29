package stringDemo;

public class ReplaceAllTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "ab2ac3";
		System.out.println(str.replaceAll("(\\w){2}.", "repalcement"));
	}

}
