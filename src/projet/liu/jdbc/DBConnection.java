package projet.liu.jdbc;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class DBConnection { 
	private static DBConnection instance; 
	private Connection connection; 
	private String url = "jdbc:mysql://localhost:3306/"; 
	private String bdd = "bibliotheque"; 
	private String username = "root"; 
	private String password = ""; 

	private DBConnection() throws SQLException { 
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			this.connection = DriverManager.getConnection(url+bdd, 
					username, password); 
			System.out.println("Connection etablie à la base de donnees : "+bdd); 
		} catch (ClassNotFoundException ex) { 
			System.out.println("Something is wrong with the DB connection String : " + ex.getMessage()); 
		} 
	} 

	public Connection getConnection() { 
		return connection; 
	} 

	public static DBConnection getInstance() throws SQLException { 
		if (instance == null) { 
			instance = new DBConnection(); 
		} else if (instance.getConnection().isClosed()) { 
			instance = new DBConnection(); 
		} 
		return instance; 
	}
	public static void main(String [] args) {
		try {
			DBConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
