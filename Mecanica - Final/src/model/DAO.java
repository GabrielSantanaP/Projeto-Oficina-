package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Database connection
 * 
 * @author gabriel.spadovesi
 * @version 1.0
 */

public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.0.50:3306/dboficina";
	private String user = "dba";
	private String password = "123@Senac";

	/**
	 * Method responsible for connecting to the database
	 * 
	 * @return con
	 *
	 */

	public Connection conectar() {
		// Create an object named con
		Connection con = null;
		// exception handling
		try {
			// the two lines below establish the connection
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
