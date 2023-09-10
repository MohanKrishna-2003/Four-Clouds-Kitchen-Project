package foodOrderingSystem;
import databaseConnection.*;
import java.util.Scanner;

class Main {
	
    
	   public static final Scanner sc = new Scanner(System.in);
	   public static void main(String args[]) throws Exception {
	      
	      System.out.println("WELCOME TO FOUR CLOUD's KITCHEN");
	      Login usr = new Login();
	      usr.loginMenu();
	      String mail = usr.process();
	      Cart cart = new Cart();
	      while(true) {
	      System.out.println("MENU\n1.Starters\n2.Pizza's\n3.Burgers's\n4.Biryani's\n5.Beverages\n6.Complete Order");
	      System.out.print("Choose your Category:");
	      int choice = sc.nextInt();
	      switch(choice) {
	      case 1 :	Starters s = new Starters();
	      			s.startersMenu();
	      			break;
	      case 2 :	Pizza p = new Pizza();
	      			p.pizzaMenu();
	      			break;
	      case 3 :	Burgers b = new Burgers();
	      			b.burgersMenu();
	      			break;
	      case 4 :	Biryani by = new Biryani();
	      			by.biryaniMenu();
					break;
	      case 5 :	Beverages bg = new Beverages();
	      			bg.beveragesMenu();
					break;
	      case 6 :  if(!cart.cartList(mail))
	      				System.exit(0);
	      			break;
		  default : System.out.println("Choose a valid option!!");
	      }
	      }
	      
	   }
}
