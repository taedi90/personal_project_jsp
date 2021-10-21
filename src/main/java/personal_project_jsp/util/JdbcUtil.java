package personal_project_jsp.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

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

		// encryptor init
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(System.getenv("TAEDI_ENC_KEY"));
		encryptor.setIvGenerator(new RandomIvGenerator());
		encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
		encryptor.setKeyObtentionIterations(2);

		
		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propPath);){
			props.load(is);
			// properties decrypt
			String url = encryptor.decrypt(props.getProperty("url"));
			String user = encryptor.decrypt(props.getProperty("user"));
			String password = encryptor.decrypt(props.getProperty("password"));

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
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
