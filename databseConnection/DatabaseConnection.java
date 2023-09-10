package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

public class DatabaseConnection {
	public Connection con = null;
    public Statement statement;
    public ResultSet result;
    public DatabaseMetaData metaData;
	public DatabaseConnection() throws ClassNotFoundException, SQLException{
		
		 try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         con = DriverManager.getConnection("jdbc:mysql://localhost/food_ordering_system", "root", "Nan@28");
	         statement  = con.createStatement();
	      } catch (SQLException e) {
	         System.out.println("*--Connection not Success--*");
	         System.out.println(" " + e.getMessage());
	      } 

	}

}
