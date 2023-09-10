package foodOrderingSystem;

import databaseConnection.DatabaseConnection;
import java.util.*;
import java.sql.SQLException;


public class Starters {
	Scanner sc = new Scanner(System.in);
	Cart cart;
	DatabaseConnection db;
	public Starters() throws ClassNotFoundException, SQLException {
		cart = new Cart();
		db = new DatabaseConnection();
	}
	public void startersMenu() throws SQLException, ClassNotFoundException {
		System.out.println("WELCOME TO STARTERS SECTION");
		System.out.println("MENU\n");
		String query = "Select * from starters";
		
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
			
			String query1 = "SELECT * from starters where s_no="+choice;
			db.result = db.statement.executeQuery(query1);
			db.result.next();
			
			String item = db.result.getString("item");
			int price =Integer.parseInt(db.result.getString("full"));
			cart.addToCart(item, price);
			
			System.out.println("Item added to cart");
		 }
	}

}
