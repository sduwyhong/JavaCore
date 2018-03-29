package NetDemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://finance.ifeng.com/stock/");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String content = null;
		while((content = br.readLine())!=null) {
			if(content.contains("深圳"))	System.out.println(content);
		}
		br.close();
		is.close();
	}

}
