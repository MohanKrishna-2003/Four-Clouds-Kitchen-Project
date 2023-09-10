package foodOrderingSystem;

import databaseConnection.DatabaseConnection;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.*;

public class Login {
	Scanner sc = new Scanner(System.in);
	DatabaseConnection db;
	public String mail; 
	
	public Login() throws ClassNotFoundException, SQLException {
		db = new DatabaseConnection();
	}
	public void loginMenu() throws SQLException {
		System.out.print("1.Login\n2.SignUp\nEnter Your Choice:");
		int choice = sc.nextInt();
		sc.nextLine();
		while(true) {
			if(choice==1) {
				login();
				break;
			}
			else if(choice==2) {
				signUp();
				break;
			}
			else 
				System.out.println("Choose a valid option");
		}
	}
	public void login() throws SQLException {
		System.out.println("~~~~~~~~~~~~~~LOGIN~~~~~~~~~~~~~~");
		System.out.print("Enter Your Mail:" );
		mail = sc.nextLine();
		System.out.print("Enter password: ");
		String pass = sc.nextLine();
		if(validMail(mail)) {
			db.result = db.statement.executeQuery("select * from customers where mail="+"\""+mail+"\"");
			if(db.result.next()) {
				db.result = db.statement.executeQuery("select * from customers where mail="+"\""+mail+"\""+"and password="+"\""+pass+"\"");
				if(db.result.next())
					System.out.println("Welcome back!!");
				else {
					System.out.println("Wrong Password");
					login();
				}	
			}
			else {
				System.out.println("NO user found\nPlease Signup");
				signUp();
			}
		}else{
			System.out.println("Invalid Mail");
			login();
		}
	}
	
	public void signUp() throws SQLException {
		System.out.println("~~~~~~~~~~~~~~SignUp~~~~~~~~~~~~~~");
		System.out.print("Enter Your Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Your Phone: ");
		String phone = sc.nextLine();
		System.out.print("Enter Your Mail: ");
		System.out.print("Enter password: ");
		String pass = sc.nextLine();
		if(validMail(mail)) {
		db.result = db.statement.executeQuery("select * from customers where mail="+"\""+mail+"\"");
		if(db.result.next()) {
				System.out.println("User Already Exist\nPlease Login");
				login();
			}
			else {
				String command = "INSERT INTO customers VALUES("+"\""+name+"\""+","+"\""+phone+"\""+","+"\""+mail+"\""+","+"\""+pass+"\""+")";
				db.statement.execute(command);
				System.out.println("Registered Successfully");	
			}
		}else{
			System.out.println("Invalid Mail");
			signUp();
		}
	}
	public boolean validMail(String mail) {
		String pattern = "@gmail.com";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(mail);
		if(matcher.find())
			return true;
		else 
			return false;
		
	}
	public String process() throws SQLException {
		System.out.print("1.Place Your order\n2.Show Previous Orders\nChoose your option:");
		int choice = sc.nextInt();
		if(choice==1)
			return mail;
		else {
			db.result = db.statement.executeQuery("select * from orders where mail="+"\""+mail+"\"");
			while(db.result.next()) {
				String orders = db.result.getString("orders");
				Date date = db.result.getDate("date");
				String amount = db.result.getString("amount");
				String invoice = db.result.getString("invoice");
				System.out.println("DATE\t\tINVOICE\t\tAMOUNT\t\tORDERS");
				System.out.println(date+"\t"+invoice+"\t"+amount+"\t"+orders);
			}
		}
		return mail;
	}
}
