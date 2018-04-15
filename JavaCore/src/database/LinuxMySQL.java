package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

/**
 * @author wyhong
 * @date 2018-4-2
 */
public class LinuxMySQL {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//com.mysql.jdbc.CommunicationsException: Communications link failure due to underlying exception: ** BEGIN NESTED EXCEPTION **  ==> centOS防火墙阻挡 ==> systemctl stop firewalld.service
			conn = DriverManager.getConnection("jdbc:mysql://192.168.184.130:3306/testdb", "root", "rootpsw");
			if(conn == null) {
				throw new RuntimeException("connect failed");
			}
			statement = (PreparedStatement) conn.prepareStatement("insert into test(id,username,password) values(?,?,?)");
			statement.setInt(1, 2);
			statement.setString(2, "wyh");
			statement.setString(3, "wyhpsw");
			System.out.println(statement.execute());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
