package foodOrderingSystem;

import java.sql.SQLException;
import java.util.Scanner;
import databaseConnection.DatabaseConnection;
import java.util.Random;
import java.time.LocalDate;

public class Cart {
	
	 DatabaseConnection db;
	 Scanner sc = new Scanner(System.in);
	 Random random;
	
	public Cart() throws ClassNotFoundException, SQLException {
		db = new DatabaseConnection();
		random = new Random();
	}
	
	public boolean table() throws SQLException {
		db.metaData = db.con.getMetaData();
        db.result = db.metaData.getTables(null, null,"cart", null);
        
        if(db.result.next())
        	return true;
        else {
        	String command = "CREATE TABLE cart (s_no INT(25) AUTO_INCREMENT PRIMARY KEY,item VARCHAR(255),price INT(10))";
        	db.statement.execute(command);
        	return false;
        }
	}
	
	public void addToCart(String item,int price) throws SQLException {
		boolean table = table();
		String command = "INSERT INTO cart(item,price)VALUES("+"\""+item+"\""+","+"\""+price+"\""+")";
		db.statement.execute(command);
	}
	
	public void showCart() throws SQLException {
		boolean table = table();
		if(!table)
			System.out.println("No items in the cart");
		else {
			String command = "SELECT * FROM cart";
			db.result = db.statement.executeQuery(command);
			while(db.result.next()) {
				int sno = db.result.getInt("s_no");
				String item = db.result.getString("item");
				int price = db.result.getInt("price");
				System.out.println(sno+". "+item+"-----$"+price);
			}
		}
	}
	
	public void removeFromCart() throws SQLException{
		showCart();
		System.out.println("Choose the item to remove:");
		int choice = sc.nextInt();
		String Command = "DELETE FROM cart WHERE s_no="+choice;
		db.statement.execute(Command);
	}
	
	public void payment(String mail) throws SQLException, ClassNotFoundException {
		String orders ="";
		String invoice = invoice();
		LocalDate date = LocalDate.now();
		
		String command = "SELECT SUM(price) FROM cart";
		db.result = db.statement.executeQuery(command);
		db.result.next();
		int tot_amount = db.result.getInt("SUM(price)");
		
		showCart();
		
		command = "SELECT * FROM cart";
		db.result = db.statement.executeQuery(command);
		while(db.result.next()) {
			String item = db.result.getString("item");
			orders = orders +"|"+ item +"|";
		}
		while(true) {
			command = "SELECT * FROM orders WHERE invoice="+invoice;
			db.result = db.statement.executeQuery(command);
			if(db.result.next()) {
				invoice = invoice();
			}else {
				break;
			}
		}
		
		command = "INSERT INTO orders VALUES ("
			    + "'" + mail + "'" + ","
			    + "'" + date + "'" + ","
			    + "'" + orders + "'" + ","
			    + "'" + tot_amount + "'" + ","
			    + "'" + invoice + "'"
			    + ")";

		db.statement.execute(command);
		
		System.out.println("Your Bill Amount is:"+tot_amount);
		System.out.println("Thank you!!!\nYour order is placed successfully...");
		db.statement.execute("DROP TABLE cart");
	}
	public boolean cartList(String mail) throws SQLException, ClassNotFoundException {
		showCart();
		
		int choice = 1;
		do {
			System.out.print("1.To add an item\n2.To remove an item\n3.Proceed to payment\nEnter your choice:");
			choice = sc.nextInt();
			if(choice>3 || choice<1)
				System.out.println("Please Choose a valid option");
			else if(choice==1)
				return true;
			else if(choice==2) {
				removeFromCart();
				showCart();
			}else {
				payment(mail);
				break;
			}
			
		}while(true);
		return false;
	}
	
	public String invoice() throws SQLException {
		StringBuilder invoice = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10); 
            invoice.append(digit);
        }
        return invoice.toString();
	}
}
