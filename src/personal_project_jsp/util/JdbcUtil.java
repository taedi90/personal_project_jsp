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
		
		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propPath);){
			props.load(is);
			String url = props.getProperty("url");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, props);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("url 혹은 user, password 확인하세요.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
		return con;
	}
	
}
