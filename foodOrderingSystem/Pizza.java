package foodOrderingSystem;

import databaseConnection.DatabaseConnection;
import java.util.Scanner;
import java.sql.SQLException;

public class Pizza {
	Scanner sc = new Scanner(System.in);
	Cart cart;
	DatabaseConnection db;
	public Pizza() throws ClassNotFoundException, SQLException {
		cart = new Cart();
		db = new DatabaseConnection();
	}
	public void pizzaMenu() throws SQLException,ClassNotFoundException {
		System.out.println("WELCOME TO PIZZA SECTION");
		System.out.println("MENU\n");
		String query = "Select * from pizza";
		
		db.result = db.statement.executeQuery(query);
		while(db.result.next()) {
			int sno = db.result.getInt("s_no");
			String item = db.result.getString("item");
			String small = db.result.getString("small");
			String medium = db.result.getString("medium");
			String large = db.result.getString("large");
			System.out.println(sno+". "+item+"-$"+small+"(small)"+"-$"+medium+"(medium)"+"-$"+large+"(large)");
		}
		System.out.println("0. Return to main menu");
		int itemChoice;
		int quantityChoice;
		while(true) {
			System.out.print("Enter your choice:");
			itemChoice = sc.nextInt();
			if(itemChoice==0)
				break;
			System.out.print("1.Small\n2.Medium\n3.Large\nEnter your choice:");
			quantityChoice  = sc.nextInt();
			
			String command = "select * from pizza where s_no="+itemChoice;
			db.result = db.statement.executeQuery(command);
			db.result.next();
			String item = db.result.getString("item");
			int price = Integer.parseInt(db.result.getString(quantityChoice+1));
			cart.addToCart(item, price);
			System.out.println("Item added to cart");
		}	
	}

}
