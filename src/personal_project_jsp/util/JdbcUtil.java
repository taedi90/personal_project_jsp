package personal_project_jsp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	
	public static Connection getConnection() {
		Connection con = null;
		String propPath = "mysql_db.properties";
		Properties props = new Properties();
		
		
		
		String url = "jdbc:mysql://localhost:3306/my_board?useSSL=false";
		String user = "test";
		String password = "test";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try (InputStream is = ClassLoader.getSystemResourceAsStream(propPath);){
//			props.load(is);
//			String url = props.getProperty("url");
//			System.out.println(url);
//			con = DriverManager.getConnection(url, props);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			System.out.println("url 혹은 user, password 확인하세요.");
//		}
		
		return con;
	}
	
}
