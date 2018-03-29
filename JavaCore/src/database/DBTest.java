package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 create table Salary(
 	firstName varchar(100),
 	lastName varchar(100),
 	rank varchar(15),
 	salary float
 );
 */
public class DBTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		//1注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2获取与数据库的连接（指定哪个数据库，用户名，密码）
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");
		//3创建代表sql语句的对象
		java.sql.Statement stmt = conn.createStatement();
		//4执行sql语句
		//用URL绑定输入流并构建缓冲读取流，这样才能一行一行读
		URL url = new URL("http://cs.armstrong.edu/liang/data/Salary.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		String temp = "";
		String[] data = null;
		//读文件
		while((temp=reader.readLine())!=null){
			data = temp.split(" ");
			//values里面的java变量名都加上单引号,否则sql里面会把变量名当字段名
			stmt.execute("insert into salary (firstName,lastName,rank,salary) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')");
		}
		System.out.println("success");
		//5释放资源
		stmt.close();
		conn.close();
	}

}
