package foodOrderingSystem;

import databaseConnection.DatabaseConnection;
import java.util.Scanner;
import java.sql.SQLException;

public class Beverages {
	Scanner sc = new Scanner(System.in);
	Cart cart;
	DatabaseConnection db;
	public Beverages() throws ClassNotFoundException, SQLException {
		cart = new Cart();
		db = new DatabaseConnection();
	}
	public void beveragesMenu() throws SQLException {
		System.out.println("WELCOME TO BEVERAGES SECTION");
		System.out.println("MENU\n");
		String query = "Select * from beverages";
		
		db.result = db.statement.executeQuery(query);
		while(db.result.next()) {
			int sno = db.result.getInt("s_no");
			String item = db.result.getString("item");
			String price = db.result.getString("full");
			System.out.println(sno+". "+item+"-----$"+price);
		}
		System.out.println("0. Return to main menu");
		
		while(true) {
			System.out.println("Enter your choice(s):");
			int choice = sc.nextInt();
			if(choice==0)
				break;
			
			String query1 = "SELECT * from beverages where s_no="+choice;
			db.result = db.statement.executeQuery(query1);
			db.result.next();
			
			String item = db.result.getString("item");
			int price =Integer.parseInt(db.result.getString("full"));
			cart.addToCart(item, price);
			
			System.out.println("Item added to cart");
		 }
	}

}
