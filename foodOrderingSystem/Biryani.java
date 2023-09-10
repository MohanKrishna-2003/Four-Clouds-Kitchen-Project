package foodOrderingSystem;

import databaseConnection.DatabaseConnection;
import java.util.Scanner;
import java.sql.SQLException;

public class Biryani {
	Scanner sc = new Scanner(System.in);
	Cart cart;
	DatabaseConnection db;
	public Biryani() throws ClassNotFoundException, SQLException {
		cart = new Cart();
		db = new DatabaseConnection();
	}
	public void biryaniMenu() throws SQLException {
		System.out.println("WELCOME TO BIRYANI SECTION");
		System.out.println("MENU\n");
		String query = "SELECT * FROM biryanis";
		
		db.result = db.statement.executeQuery(query);
		while(db.result.next()) {
			int sno = db.result.getInt("s_no");
			String item = db.result.getString("item");
			String price = db.result.getString("full");
			System.out.println(sno+". "+item+"-----$"+price);
		}
		System.out.println("0. Return to main menu");
		int choice;
		while(true) {
			System.out.print("Enter your choice:");
			choice = sc.nextInt();
			if(choice==0)
				break;
			
			String query1 = "SELECT * from biryanis where s_no="+choice;
			db.result = db.statement.executeQuery(query1);
			db.result.next();
			
			String item = db.result.getString("item");
			int price =Integer.parseInt(db.result.getString("full"));
			cart.addToCart(item, price);
			
			System.out.println("Item added to cart");
		}
	}
}
