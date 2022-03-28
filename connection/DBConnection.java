package in.sts.assignment2.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;



public class DBConnection {
	final static Logger log=Logger.getLogger(DBConnection.class);
	
	/*
	 * 
	 * getConnection method for Database Connection
	 */
	
	public static Connection getConnection() {
		Connection connection=null;
		
		String url="jdbc:mysql://localhost:3306/employee";
		String user="root";
		String password="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url, user, password);
			
		}
		catch (SQLException sqlException) {
			log.error("sql exception");
		}catch (ClassNotFoundException classNotFoundException) {
			log.error("Class Not Found");
		}
		
		return connection;




	}
}
