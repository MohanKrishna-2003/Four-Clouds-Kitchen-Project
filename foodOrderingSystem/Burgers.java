package foodOrderingSystem;

import databaseConnection.DatabaseConnection;
import java.util.Scanner;
import java.sql.SQLException;

public class Burgers {
	Scanner sc = new Scanner(System.in);
	DatabaseConnection db;
	Cart cart;
	public Burgers() throws ClassNotFoundException, SQLException {
		db = new DatabaseConnection();
		cart = new Cart();
	}
	public void burgersMenu() throws SQLException {
		System.out.println("WELCOME TO BURGER SECTION");
		System.out.println("MENU\n");
		String query = "Select * from burgers";
		
		db.result = db.statement.executeQuery(query);
		while(db.result.next()) {
			int sno = db.result.getInt("s_no");
			String item = db.result.getString("item");
			String regular = db.result.getString("regular");
			String large = db.result.getString("large");
			System.out.println(sno+". "+item+"-$"+regular+"(regular)"+"-$"+large+"(large)");
		}
		System.out.println("0. Return to main menu");
		int itemChoice;
		int quantityChoice;
		while(true) {
			System.out.print("Enter your choice:");
			itemChoice = sc.nextInt();
			if(itemChoice==0)
				break;
			System.out.print("1.Regular\n2.Large\nEnter your choice:");
			quantityChoice = sc.nextInt();
			
			String command = "SELECT * FROM burgers WHERE s_no="+itemChoice;
			db.result = db.statement.executeQuery(command);
			db.result.next();
			String item = db.result.getString("item");
			int price = Integer.parseInt(db.result.getString(quantityChoice+1));
			cart.addToCart(item, price);
			System.out.println("Item added to cart");
		}
	}

}
