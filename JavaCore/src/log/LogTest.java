package log;

import org.apache.log4j.Logger;

/**
 * @author wyhong
 * @date 2018-4-15
 */
public class LogTest {
	
	private static final Logger LOGGER = Logger.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			LOGGER.debug(i);
		}
	}
}
