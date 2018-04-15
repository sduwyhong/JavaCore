package digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;

/**
 * @author wyhong
 * @date 2018-4-5
 */
public class SHA256 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String message = "abcd";
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(message.getBytes("utf-8"));
			byte[] hash = digest.digest();
			System.out.println(Hex.encodeHex(hash));
			System.out.println(new Date().getTime());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
