package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	
	static{
		try {
		InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("dbcfg.properties");
		Properties props = new Properties();
		props.load(in);
		
		driverClass = props.getProperty("driverClass");
		url = props.getProperty("url");
		user = props.getProperty("user");
		password = props.getProperty("password")
				;
		Class.forName(driverClass);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		
	}
	
	public static Connection getConnection() throws Exception{

		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public static void release(Connection conn, Statement stmt, ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
